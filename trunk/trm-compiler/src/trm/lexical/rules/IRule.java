package trm.lexical.rules;

/** Interface IRule
 * @author TRM
 * @version 0.99
 */
public interface IRule {

    /** Método de avaliação de uma regra geral
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    boolean evaluate(char transitionChar);
}
