package trm.lexical.rules;

/** Classe para execução de regras de verificação de digitos. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class IsDigitRule implements IRule {

    /** Método de avaliação da regra de digito:
     * caso o elemento c (char) seja um digito,
     * retorna true. Caso contrario retorna false.
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isDigit(transitionChar);
    }
}
