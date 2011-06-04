package trm.lexical;

/**
 * Tipo de palavra reconhecida.
 */
public enum WordType {

    WORD, UNDEFINED, INTEGER_CTE, REAL_CTE, CHARACTER_CTE, STRING_CTE, SPACE, OPEN_PARENTHESES,
    CLOSE_PARENTHESES, OPEN_CURLY_BRACKET, CLOSE_CURLY_BRACKET, ADD, SUB, MULT, DIV, AND, OR,
    EQUAL, GREATER_THAN, LESS_THAN, OPEN_SQUARE_BRACKET, CLOSE_SQUARE_BRACKET, SEMICOLON, COLON,
    GREATER_OR_EQUAL, LESS_OR_EQUAL, NOT_EQUAL, ATRIBUTION
}