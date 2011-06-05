package trm.lexical.rules;

/** Classe ExclusiveRule para execução de regras exclusivas. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class ExclusiveRule implements IRule {

    private char[] excludedChars;

    /** Construtor ExclusiveRule da Classe
     * @param  excludedChars char... - Todos os caractereres não permitidos
     */
    public ExclusiveRule(char... excludedChars) {
        this.excludedChars = excludedChars;
    }

    /** Método que retorna os caracteres não permitidos
     * @return char[] - Array com os caracteres não permitidos
     */
    public char[] getExcludedChars() {
        return excludedChars;
    }

    /** Método de avaliação da regra exclusiva:
     * caso o elemento c (char) pertença a lista
     * de não permitidos, retorna false. Caso contrario
     * retorna true.
     * @param  c char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char c) {
        for (int i = 0; i < excludedChars.length; i++) {
            if (excludedChars[i] == c) {
                return false;
            }
        }
        return true;
    }
}
