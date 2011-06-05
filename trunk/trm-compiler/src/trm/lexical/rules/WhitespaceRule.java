package trm.lexical.rules;

/** Classe para execução de regras de verificação de espacos em branco. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class WhitespaceRule implements IRule {

    /** Método de avaliação da regra de espacos em branco:
     * caso o elemento c (char) seja um espaco em branco,
     * retorna true. Caso contrario retorna false.
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char transitionChar) {
        return Character.isWhitespace(transitionChar);
    }
}
