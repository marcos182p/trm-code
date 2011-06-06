package trm.lexical;

/** Interface IRule - Responsável por analisar determinada entidade.
 * @author TRM
 * @version 0.99
 */
public interface ILexical {

    /** Método que analisa determinada entidade.
     * @return Token - Proximo token gerado
     * depois da avaliação/análise
     */
    Token nextToken();
}
