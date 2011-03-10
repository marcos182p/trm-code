package rtm.lexical;

import java.util.Collection;

/**
 * Interface reponsavel por analisar determinada entidade.
 */
public interface Lexical {

    /**
     * Analisar determinada entidade.
     * 
     * @return lista de tokens.
     */
    Collection<Token> analyse();
}
