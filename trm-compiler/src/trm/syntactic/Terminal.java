package trm.syntactic;

import trm.lexical.TokenClass;

/** Classe Terminal - Extends Element -
 * Responsável definir um elemento terminal da gramática
 * @author TRM
 * @version 0.99
 */
public class Terminal extends Element {

    private TokenClass tokenClass;

    /** Construtor Terminal da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  tokenClass TokenClass - Classe do token
     */
    public Terminal(TokenClass tokenClass) {
        this.tokenClass = tokenClass;
    }

    @Override
    /** Método que retorna o label do elemento terminal
     * @return String - Label do elemento
     */
    public String getLabel() {
        return tokenClass.name();
    }

    @Override
    /** Metodo que retorna uma String do terminal
     * @return String - String representando o simbolo terminal,
     */
    public String toString() {
        return getLabel();
    }
}
