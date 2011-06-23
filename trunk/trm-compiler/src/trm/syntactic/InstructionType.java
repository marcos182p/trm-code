package trm.syntactic;

/** Enumeração InstructionType - Define os tipos de instruções possíveis
 * @author TRM 
 * @version 0.99
 */
public enum InstructionType {

    /*
     * Tipo declaração
     */
    DECLARATION,
    /*
     * Tipo atribuição
     */
    ATTRIBUTION,
    /*
     * Tipo for
     */
    FOR,
    /*
     * Tipo while
     */
    WHILE,
    /*
     * Tipo função
     */
    FUNCTION,
    /*
     * Tipo chamada de função
     */
    FUNCTION_CALL,
    /*
     * Tipo if
     */
    IF,
    /*
     * Tipo else
     */
    ELSE,
    /*
     * Tipo fim de bloco
     */
    END_BLOCK,
    /*
     * Tipo retorno
     */
    RETURN
}
