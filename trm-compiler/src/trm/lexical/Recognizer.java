package trm.lexical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Classe Recognizer - verifica se uma fita é reconhecida por um automato.
 * @author TRM
 * @version 0.99
 */
public class Recognizer {

    private Automaton automaton;
    private State lastState;
    private State currentState;
    private StringBuilder currentWord;
    private List<Token> tokens;
    private char[] tape;
    private Map<String, TokenClass> tokenMap;
    private int lineGenerate = 1;
    private int columnGenerate = 1;
    private int indexRead = 0;

    /** Construtor Recognizer da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro. Inicializa a lista de tokens com palavras
     * chave e chama o metodo reset que prepara a inicialização
     * do reconhecedor.
     * @param  automaton Automaton - Automato que será utilizado para
     * o reconhecimento da fita
     * @param  tape char[] - Array de caracteres representando a fita
     */
    public Recognizer(Automaton automaton, char[] tape) {

        this.automaton = automaton;
        this.tape = tape;
        initTokens();
        reset();
    }

    /** Método que retorna a lista de Tokens
     * @return List<Token> - Lista de tokens reconhecidos
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /** Método que prepara a inicialização do reconhecedor.
     *
     * @return void
     */
    private void reset() {
        this.currentState = automaton.getStartState();
        this.currentWord = new StringBuilder();
    }

    /** Método que gera um novo token com as informações
     * contidas nos atributos da classe
     * @return Token - Token Gerado
     */
    private Token generateToken() {

        if (!automaton.getFinalStates().contains(currentState)) {
            throw new RuntimeException("Impossivel gerar token.");
        }

        String value = currentWord.toString();

        Token token = new Token(value, getTokenClass(currentState, value),
                this.lineGenerate, this.columnGenerate - (value.length() + 1));
        tokens.add(token);
        System.out.println(token);
        return token;

    }

    /** Método que verifica se a fita é reconhecida
     * pelo automato.
     * @exception TransitionException - Tratada pelo
     * próprio método
     * @return Token - Token Gerado
     */
    public Token run() {

        reset();

        while (indexRead < tape.length) {
            try {
                transition(tape[indexRead]);

                currentWord.append(tape[indexRead]);

                if (tape[indexRead] == System.getProperty("line.separator").
                        toCharArray()[0]) {
                    this.lineGenerate++;
                    this.columnGenerate = 0;
                }

                if (currentState.equals(automaton.getStartState())) {
                    reset();
                }

                indexRead++;
                this.columnGenerate++;
            } catch (TransitionException e) {
                Token token = generateToken();
                reset();
                return token;
            }

        }
        Token token = null;
        if (!currentState.equals(automaton.getStartState())) {
            token = generateToken();
        }

        return token;
    }

    /** Método que inicializa a lista com tokens reservados
     * @return void
     */
    private void initTokens() {
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
        this.tokenMap.put("not", TokenClass.TK_NOT);
        this.tokenMap.put("mod", TokenClass.TK_MOD);
        this.tokenMap.put("and", TokenClass.TK_AND);
        this.tokenMap.put("or", TokenClass.TK_OR);
        this.tokenMap.put("in", TokenClass.TK_IN);
    }

    /** Método que retorna a classe do Token
     *
     * @param  state State - Estado que o automato se localiza
     * @param  word String - palavra lida (para o caso de IDs)
     * @return TokenClass - Classe do Token
     */
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
            case OPEN_PARENTHESES:
                result = TokenClass.TK_OPEN_PARENTHESES;
                break;
            case CLOSE_PARENTHESES:
                result = TokenClass.TK_CLOSE_PARENTHESES;
                break;
            case OPEN_CURLY_BRACKET:
                result = TokenClass.TK_OPEN_CURLY_BRACKET;
                break;
            case CLOSE_CURLY_BRACKET:
                result = TokenClass.TK_CLOSE_CURLY_BRACKET;
                break;
            case ADD:
                result = TokenClass.TK_ADD;
                break;
            case SUB:
                result = TokenClass.TK_SUB;
                break;
            case MULT:
                result = TokenClass.TK_MULT;
                break;
            case DIV:
                result = TokenClass.TK_DIV;
                break;
            case EQUAL:
                result = TokenClass.TK_EQUAL;
                break;
            case GREATER_THAN:
                result = TokenClass.TK_GREATER_THAN;
                break;
            case LESS_THAN:
                result = TokenClass.TK_LESS_THAN;
                break;
            case OPEN_SQUARE_BRACKET:
                result = TokenClass.TK_OPEN_SQUARE_BRACKET;
                break;
            case CLOSE_SQUARE_BRACKET:
                result = TokenClass.TK_CLOSE_SQUARE_BRACKET;
                break;
            case SEMICOLON:
                result = TokenClass.TK_SEMICOLON;
                break;
            case COLON:
                result = TokenClass.TK_COLON;
                break;
            case GREATER_OR_EQUAL:
                result = TokenClass.TK_GREATER_OR_EQUAL;
                break;
            case LESS_OR_EQUAL:
                result = TokenClass.TK_LESS_OR_EQUAL;
                break;
            case NOT_EQUAL:
                result = TokenClass.TK_NOT_EQUAL;
                break;
            case ATRIBUTION:
                result = TokenClass.TK_ATTRIBUTION;
                break;
            case COMMAN:
                result = TokenClass.TK_COMMAN;
                break;
            default:
                result = TokenClass.TK_UNDEFINED;
                break;
        }

        return result;
    }

    /** Método que procura uma transição baseado no
     * proximo caractere lido
     * @param  c char - Caracterer lido
     * @return void
     */
    private void transition(char c) {

        State target = automaton.nextTargetState(currentState, c);


        if (target == null) {
            throw new TransitionException("Transição para esse valor não "
                    + "encontrada.");
        }

        lastState = currentState;
        currentState = target;


    }
}

/** Classe TransitionException - Exceção que sinaliza quando
 * não existe mais transição em um estado para a leitura
 * de um valor. extends RuntimeException .
 * @author TRM
 * @version 0.99
 */
class TransitionException extends RuntimeException {

    /** Construtor TransitionException da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro.
     * @param  cause Throwable - causa da Exception
     */
    public TransitionException(Throwable cause) {
        super(cause);
    }

    /** Construtor TransitionException da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro.
     * @param  message String - mensagem da Exception
     * @param  cause Throwable - causa da Exception
     */
    public TransitionException(String message, Throwable cause) {
        super(message, cause);
    }

    /** Construtor TransitionException da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro.
     * @param  message String - mensagem da Exception
     */
    public TransitionException(String message) {

        super(message);
    }
}
