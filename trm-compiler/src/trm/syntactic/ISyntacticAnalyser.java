package trm.syntactic;

import trm.lexical.ILexical;

/** Interface ISyntacticAnalyser - Responsável por analisar determinada entidade.
 * @author TRM
 * @version 0.99
 */
public interface ISyntacticAnalyser {

    /** Método que analisa determinada entidade.
     * @param lexical ILexical - Analizador léxico
     */
    public void parse(ILexical lexical);
}
