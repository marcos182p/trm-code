package trm.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que verifica se uma fita é reconhecida por um automato.
 */
public class Recognizer {

    private static final Logger LOGGER = Logger.getLogger(Recognizer.class.getName());
    private Automaton automaton;
    private State lastState;
    private State currentState;
    private StringBuilder currentWord;
    private List<Token> tokens;
    private char[] tape;
    private Map<String, TokenClass> tokenMap;

    public Recognizer(Automaton automaton, char[] tape) {

        this.automaton = automaton;

        this.tape = tape;

        this.tokens = new ArrayList<Token>();

        this.tokenMap = new HashMap<String, TokenClass>();

        this.tokenMap.put("string", TokenClass.TK_STRING);

        this.tokenMap.put("while", TokenClass.TK_WHILE);

        this.tokenMap.put("for", TokenClass.TK_FOR);

        this.tokenMap.put("if", TokenClass.TK_IF);

        this.tokenMap.put("else", TokenClass.TK_ELSE);

        this.tokenMap.put("switch", TokenClass.TK_SWITCH);

        this.tokenMap.put("break", TokenClass.TK_BREAK);

        this.tokenMap.put("integer", TokenClass.TK_INTEGER);

        this.tokenMap.put("real", TokenClass.TK_REAL);

        this.tokenMap.put("boolean", TokenClass.TK_BOOLEAN);

        this.tokenMap.put("character", TokenClass.TK_CHARACTER);

        this.tokenMap.put("void", TokenClass.TK_VOID);

        this.tokenMap.put("true", TokenClass.TK_BOOLEAN_CTE);

        this.tokenMap.put("false", TokenClass.TK_BOOLEAN_CTE);


        reset();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void reset() {
        this.currentState = automaton.getStartState();
        this.currentWord = new StringBuilder();
    }

    private void generateToken() {

        if (!automaton.getFinalStates().contains(currentState)) {
            LOGGER.log(Level.WARNING, "erro ao criar " + currentWord);
            throw new RuntimeException("Impossivel gerar token.");
        }

        String value = currentWord.toString();

        Token token = new Token(value, getTokenClass(currentState, value));
        tokens.add(token);

        LOGGER.log(Level.INFO, "token criado " + token, token);

    }

    /**
     * Verifica se a fita é reconhecida pelo automato.
     */
    public void run() {

        reset();

        int index = 0;

        while (index < tape.length) {
            try {
                transition(tape[index]);

                currentWord.append(tape[index]);

                if (currentState.equals(automaton.getStartState())) {
                    reset();
                }

                index++;

            } catch (TransitionException e) {
                generateToken();
                reset();
            }

        }
        if (!currentState.equals(automaton.getStartState())) {
            generateToken();
        }

    }

    //FIXME terminar de implementar esse metodo.
    private TokenClass getTokenClass(State state, String word) {

        TokenClass result = null;

        switch (state.getDescription().getWordType()) {

            case WORD:
                result = tokenMap.get(word);
                if (result == null) {
                    result = TokenClass.TK_ID;
                }
                break;
            case INTEGER_CTE:
                result = TokenClass.TK_INTEGER_CTE;
                break;
            case REAL_CTE:
                result = TokenClass.TK_REAL_CTE;
                break;
            case CHARACTER_CTE:
                result = TokenClass.TK_CHARACTER_CTE;
                break;
            case STRING_CTE:
                result = TokenClass.TK_STRING_CTE;
                break;
        }

        return result == null ? TokenClass.TK_UNDEFINED : result;
    }

    private void transition(char c) {

        State target = automaton.nextState(currentState, c);


        if (target == null) {
            throw new TransitionException("Transição para esse valor não " + "encontrada.");
        }

        lastState = currentState;
        currentState = target;


    }
}

/**
 * Exceção que sinaliza quando não existe mais transição em um estado para
 * leitura de um valor.
 */
class TransitionException extends RuntimeException {

    public TransitionException(Throwable cause) {
        super(cause);
    }

    public TransitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransitionException(String message) {

        super(message);
    }
}
