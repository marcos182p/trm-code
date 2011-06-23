package trm.syntactic;

import java.util.Collections;
import java.util.List;
import trm.lexical.Token;

/** Classe Instruction - Responsável definir o formato de uma instrução na linguagem
 * @author TRM
 * @version 0.99
 */
public class Instruction {

    private List<Token> tokens;
    private InstructionType type;
    /*
     * Inteiro representando as linhas inicial (start) e final (end)
     * da instrução. Note que linhas de comentários e linhas vazias
     * não entram nessa contagem
     */
    private int start;
    private int end;

    /** Construtor Instruction da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  type InstructionType - Tipo de instrução
     * @param  tokens List<Token> - Lista contendo os tokens da instrução
     */
    public Instruction(InstructionType type, List<Token> tokens) {
        this.tokens = tokens;
        this.type = type;
    }

    /** Método que retorna a lista de Tokens
     * @return List<Token> - Lista de tokens
     */
    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    /** Método que retorna o tipo da instrução
     * @return InstructionType - Tipo da instrução
     */
    public InstructionType getType() {
        return type;
    }

    /** Método que retorna o ultimo token da instrução
     * @return Token - Ultimo token da instrução
     */
    public Token lastToken() {
        return tokens.get(tokens.size() - 1);
    }

    /** Método que adiciona um token para a instrução
     * @param token Token - Token a ser adicionado
     */
    public void addToken(Token token) {
        tokens.add(token);
    }

    /** Método que seta a linha final da instrução
     * @param end int - Inteiro com o valor da linha final
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /** Método que seta a linha inicial da instrução
     * @param start int - Inteiro com o valor da linha inicial
     */
    public void setStart(int start) {
        this.start = start;
    }

    /** Método que retorna a linha inicial da instrução
     * @return int - Inteiro com o valor da linha inicial
     */
    public int getStart() {
        return start;
    }

    /** Método que retorna a linha final da instrução
     * @return int - Inteiro com o valor da linha final
     */
    public int getEnd() {
        return end;
    }
}
