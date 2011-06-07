package trm.lexical;

/** Classe Token - Definir o formato de um token
 * @author TRM
 * @version 0.99
 */
public class Token {

    private TokenClass tokenClass;
    private String value;
    private int line;
    private int column;

    /** Construtor Token da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  value String - Valor do token
     * @param  token TokenClass - Classe da qual o token pertence
     * @param  line int - Linha que o token está na "matriz de tokens"
     * @param  column int - Coluna que o token está na "matriz de tokens"
     */
    public Token(String value, TokenClass token, int line, int column) {
        this.value = value;
        this.tokenClass = token;
        this.line = line;
        this.column = column;
    }

    /** Método que retorna a classe do Token
     * @return TokenClass - Classe do Token
     */
    public TokenClass getTokenClass() {
        return this.tokenClass;
    }

    /** Metodo que seta o valor do Token
     * @param  value String - Novo valor do Token
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** Método que retorna o valor do Token
     * @return String - Valor do Token
     */
    public String getValue() {
        return this.value;
    }

    /** Método que retorna a coluna que o token está na "matriz de tokens"
     * @return int - Inteiro representando a coluna do token na
     * "matriz de tokens"
     */
    public int getcolumn() {
        return column;
    }

    /** Metodo que seta a coluna que o token está na "matriz de tokens"
     * @param  column int - Novo valor da coluna
     */
    public void setcolumn(int column) {
        this.column = column;
    }

    /** Método que retorna a linha que o token está na "matriz de tokens"
     * @return int - Inteiro representando a linha do token na
     * "matriz de tokens"
     */
    public int getLine() {
        return line;
    }

    /** Metodo que seta a linha que o token está na "matriz de tokens"
     * @param  line int - Novo valor da linha
     */
    public void setLine(int line) {
        this.line = line;
    }

    /** Metodo que retorna uma String do token
     * @return String - String representando o token,
     * formato:
     * [ classeToken : valorToken : linha : coluna]
     */
    @Override
    public String toString() {
        return "[ " + tokenClass + " : " + value + " : " + line + " : "
                + column + " ]";
    }
}
