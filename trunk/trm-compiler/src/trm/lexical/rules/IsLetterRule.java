package trm.lexical.rules;

/** Classe para execução de regras de verificação de Letras. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class IsLetterRule implements IRule {

    /** Método de avaliação da regra de letras:
     * caso o elemento c (char) seja uma letra,
     * retorna true. Caso contrario retorna false.
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char transitionChar) {
        return (Character.isLetter(transitionChar));
    }
}
