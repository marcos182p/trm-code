package trm.lexical;

import java.util.Collection;

/** Interface IRule - Responsável por analisar determinada entidade.
 * @author TRM
 * @version 0.99
 */
public interface ILexical {

    /** Método que analisa determinada entidade.
     * @return Collection<Token> - Lista de tokens gerados
     * depois da avaliação/análise
     */
    Collection<Token> parse();
}