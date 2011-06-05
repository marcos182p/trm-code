package trm.lexical.rules;

/** Classe para execução de regras de verificação de Simbolos. Implementa a interface IRule
 * @author TRM
 * @version 0.99
 */
public class IsSymbolRule implements IRule {

    char[] simbolos = {'_', '+', '-', '=', '[', ']', '{', '}', '^', '~', '`', '´', ',', '”', '.',
        ';', ':', '/', '?', '\\', '\'', '¬', '¹', '²', '³', '£', '¢', '§', 'ª', 'º', '°', '·', '!',
        '@', '#', '$', '%', '&', '*', '(', ')'};

    /** Método de avaliação da regra de simbolos:
     * caso o elemento c (char) seja um simbolo
     * retorna true. Caso contrario retorna false.
     * @param  transitionChar char - Caracterer a ser avaliado
     * @return boolean - Resultado da avaliação
     */
    @Override
    public boolean evaluate(char transitionChar) {

        for (int i = 0; i < simbolos.length; i++) {
            if (transitionChar == simbolos[i]) {
                return true;
            }
        }

        return false;
    }
}
