package trm.lexical;

public enum TokenClass {

    TK_UNDEFINED(true),
    TK_ID(false),
    TK_STRING_CTE(false),
    TK_INTEGER_CTE(false),
    TK_REAL_CTE(false),
    TK_CHARACTER_CTE(false),
    TK_BOOLEAN_CTE(false),
    TK_STRING(true),
    TK_WHILE(true),
    TK_FOR(true),
    TK_IF(true),
    TK_ELSE(true),
    TK_SWITCH(true),
    TK_BREAK(true),
    TK_INTEGER(true),
    TK_REAL(true),
    TK_CHARACTER(true),
    TK_BOOLEAN(true),
    TK_VOID(true),
    TK_ADD(true),
    TK_SUB(true),
    TK_MULT(true),
    TK_DIV(true),
    TK_MOD(true),
    TK_POSITIVE(true),
    TK_NEGATIVE(true),
    TK_AND(true),
    TK_OR(true),
    TK_NOT(true),
    TK_EQUAL(true),
    TK_NOT_EQUAL(true),
    TK_GREATER_THAN(true),
    TK_LESS_THAN(true),
    TK_ATTRIBUTION(true),
    TK_GREATER_OR_EQUAL(true),
    TK_LESS_OR_EQUAL(true),
    TK_OPEN_SQUARE_BRACKET(true),
    TK_CLOSE_SQUARE_BRACKET(true),
    TK_SEMICOLON(true),
    TK_COLON(true),
    TK_OPEN_PARENTHESES(true),
    TK_CLOSE_PARENTHESES(true),
    TK_OPEN_CURLY_BRACKET(true),
    TK_CLOSE_CURLY_BRACKET(true);
    private boolean univoque;

    private TokenClass(boolean univoque) {
        this.univoque = univoque;
    }

    public boolean isUnivoque() {
        return univoque;
    }
}
