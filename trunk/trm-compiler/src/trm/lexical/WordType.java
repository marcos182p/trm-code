package trm.lexical;

/** Enumerção para o tipo de palavras que a linguagem reconhece.
 * @author TRM
 * @version 0.99
 */
public enum WordType {

    /**
     *Palavras
     */
    WORD,
    /**
     *Palavras não definidas
     */
    UNDEFINED,
    /**
     * Inteiro constantes (valores)
     */
    INTEGER_CTE,
    /**
     * Real constantes (valores)
     */
    REAL_CTE,
    /**
     * Caracter constantes (valores)
     */
    CHARACTER_CTE,
    /**
     * String constantes (valores)
     */
    STRING_CTE,
    /**
     * Espaco em Branco
     */
    SPACE,
    /**
     * Abre parênteses
     */
    OPEN_PARENTHESES,
    /**
     * Fecha parênteses
     */
    CLOSE_PARENTHESES,
    /**
     * Abre Chaves
     */
    OPEN_CURLY_BRACKET,
    /**
     * Fecha Chaves
     */
    CLOSE_CURLY_BRACKET,
    /**
     * Operador adicionar (+)
     */
    ADD,
    /**
     * Operador subtrair (-)
     */
    SUB,
    /**
     * Operador multiplicar (*)
     */
    MULT,
    /**
     * Operador dividir (/)
     */
    DIV,
    /**
     * Operador igual (=)
     */
    EQUAL,
    /**
     * Operador maior que (>)
     */
    GREATER_THAN,
    /**
     * Operador menor que (<)
     */
    LESS_THAN,
    /**
     * Abre Colchetes
     */
    OPEN_SQUARE_BRACKET,
    /**
     * Fecha Colchetes
     */
    CLOSE_SQUARE_BRACKET,
    /**
     * Ponto e vírgula
     */
    SEMICOLON,
    /**
     * Dois Pontos
     */
    COLON,
    /**
     * Operador maior ou igual (>=)
     */
    GREATER_OR_EQUAL,
    /**
     * Operador menor ou igual (<=)
     */
    LESS_OR_EQUAL,
    /**
     * Operador não-igual(!=)
     */
    NOT_EQUAL,
    /**
     * Operador atribuição (<-)
     */
    ATRIBUTION,
    /**
     * virgula (,)
     */
    COMMAN
}
