package trm.lexical.rules;

/** Classe InclusiveRule para execução de regras inclusivas. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class InclusiveRule implements IRule {

    private char[] transitionChars;

    /** Construtor InclusiveRule da Classe
     * @param  transitionChars char... - Todos os caractereres permitidos
     */
    public InclusiveRule(char... transitionChars) {
        this.transitionChars = transitionChars;
    }

    /** Método que retorna os caracteres permitidos
     * @return char[] - Array com os caracteres permitidos
     */
    public char[] getTransitionChars() {
        return this.transitionChars;
    }

    /** Método de avaliação da regra inclusiva:
     * caso o elemento c (char) pertença a lista
     * de permitidos, retorna true. Caso contrario
     * retorna false.
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char transitionChar) {
        for (int i = 0; i < transitionChars.length; i++) {
            if (transitionChars[i] == transitionChar) {
                return true;
            }
        }
        return false;
    }
}
