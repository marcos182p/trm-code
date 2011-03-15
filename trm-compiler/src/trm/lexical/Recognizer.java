package trm.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que verifica se uma fita é reconhecida por um automato.
 */
public class Recognizer {

    private Automaton automaton;

    private char[] tape;
    
    private State currentState;

    private List<Token> tokens;

    private Map<String, TokenClass> tokenMap;
    
    private boolean recognized;

    private StringBuilder word;

    public Recognizer(Automaton automaton, char[] tape) {

        this.automaton = automaton;

        this.tape = tape;
        
        this.recognized = false;

        this.tokens = new ArrayList<Token>();

        this.tokenMap = new HashMap<String, TokenClass>();

        this.tokenMap.put("enquanto", TokenClass.TK_WHILE);

        this.tokenMap.put("escolha", TokenClass.TK_SWITCH);

        this.tokenMap.put("se", TokenClass.TK_IF);

        reset();
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void reset() {
        this.currentState = automaton.getStartState();
        this.word = new StringBuilder();
    }

    private void generateToken(State state, StringBuilder word) {
         String value = word.toString().trim();
         Token token = new Token(getTokenClass(currentState,value));
         token.setValue(value);
         tokens.add(token);
    }
    /**
     * Verifica se a fita é reconhecida pelo automato.
     */
    public void run() {
        reset();
        for (char c : tape) {

            try {
                
                transition(c);
                word.append(c);
                
            } catch (TransitionException e) {

                if(!automaton.getFinalStates().contains(currentState)) {
                    throw new RuntimeException("Impossivel ler fita", e);
                }
                generateToken(currentState, word);
                reset();

            }
        }

        generateToken(currentState, word);

        //FIXME o que fazer com a ultima palavra?

        if(automaton.getFinalStates().contains(currentState)) {
            recognized = true;
        }
    }

    //FIXME terminar de implementar esse metodo.
    private TokenClass getTokenClass(State state, String word) {
        
        TokenClass result = null;

        switch(state.getDescription().getWordType()) {

            case WORD:
                result = tokenMap.get(word);
                break;
            case INTEGER_CTE:
                result = TokenClass.TK_INTEGER_CTE;
                break;
            case FLOATING_CTE:
                result = TokenClass.TK_FLOATING_CTE;
                break;

        }

        return result == null ? TokenClass.TK_ID : result;
    }

    public boolean isRecognized() {
        return recognized;
    }

    private void transition(char c) {

        State target = automaton.nextState(currentState, c);


        if (target == null) {
            throw new TransitionException("Transição para esse valor não "
                    + "encontrada.");
        }
        
        currentState = target;
    }

    /**
     * Exceção que sinaliza quando não existe mais transição em um estado para
     * leitura de um valor.
     */
    private class TransitionException extends RuntimeException {

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

}
