package trm.lexical;

/** Enumerção para a Classe de Tokens que a linguagem aceita.
 * @author TRM
 * @version 0.99
 */
public enum TokenClass {

    /**
     * Classe indefinida - unívoca
     */
    TK_UNDEFINED(true),
    /**
     * Classe id - não-unívoca
     */
    TK_ID(false),
    /**
     * Classe string constante - não-unívoca
     */
    TK_STRING_CTE(false),
    /**
     * Classe inteiro constante - não-unívoca
     */
    TK_INTEGER_CTE(false),
    /**
     * Classe real constante - não-unívoca
     */
    TK_REAL_CTE(false),
    /**
     * Classe caracter constante - não-unívoca
     */
    TK_CHARACTER_CTE(false),
    /**
     * Classe boolean constante - não-unívoca
     */
    TK_BOOLEAN_CTE(false),
    /**
     * Classe string - unívoca
     */
    TK_STRING(true),
    /**
     * Classe while - unívoca
     */
    TK_WHILE(true),
    /**
     * Classe for - unívoca
     */
    TK_FOR(true),
    /**
     * Classe if - unívoca
     */
    TK_IF(true),
    /**
     * Classe else - unívoca
     */
    TK_ELSE(true),
    /**
     * Classe switch - unívoca
     */
    TK_SWITCH(true),
    /**
     * Classe break - unívoca
     */
    TK_BREAK(true),
    /**
     * Classe integer - unívoca
     */
    TK_INTEGER(true),
    /**
     * Classe real - unívoca
     */
    TK_REAL(true),
    /**
     * Classe character - unívoca
     */
    TK_CHARACTER(true),
    /**
     * Classe boolean - unívoca
     */
    TK_BOOLEAN(true),
    /**
     * Classe void - unívoca
     */
    TK_VOID(true),
    /**
     * Classe add - unívoca
     */
    TK_ADD(true),
    /**
     * Classe sub - unívoca
     */
    TK_SUB(true),
    /**
     * Classe mult - unívoca
     */
    TK_MULT(true),
    /**
     * Classe div - unívoca
     */
    TK_DIV(true),
    /**
     * Classe mod - unívoca
     */
    TK_MOD(true),
    /**
     * Classe positive - unívoca
     */
    TK_POSITIVE(true),
    /**
     * Classe negative - unívoca
     */
    TK_NEGATIVE(true),
    /**
     * Classe and - unívoca
     */
    TK_AND(true),
    /**
     * Classe or - unívoca
     */
    TK_OR(true),
    /**
     * Classe not - unívoca
     */
    TK_NOT(true),
    /**
     * Classe in - unívoca
     */
    TK_IN(true),
    /**
     * Classe equal - unívoca
     */
    TK_EQUAL(true),
    /**
     * Classe not equal - unívoca
     */
    TK_NOT_EQUAL(true),
    /**
     * Classe maior que - unívoca
     */
    TK_GREATER_THAN(true),
    /**
     * Classe menor que - unívoca
     */
    TK_LESS_THAN(true),
    /**
     * Classe atribuição - unívoca
     */
    TK_ATTRIBUTION(true),
    /**
     * Classe maior ou igual - unívoca
     */
    TK_GREATER_OR_EQUAL(true),
    /**
     * Classe menor ou igual - unívoca
     */
    TK_LESS_OR_EQUAL(true),
    /**
     * Classe abre colchete - unívoca
     */
    TK_OPEN_SQUARE_BRACKET(true),
    /**
     * Classe fecha colchete - unívoca
     */
    TK_CLOSE_SQUARE_BRACKET(true),
    /**
     * Classe ponto e vírgula - unívoca
     */
    TK_SEMICOLON(true),
    /**
     * Classe dois pontos - unívoca
     */
    TK_COLON(true),
    /**
     * Classe abre parênteses - unívoca
     */
    TK_OPEN_PARENTHESES(true),
    /**
     * Classe fecha parênteses- unívoca
     */
    TK_CLOSE_PARENTHESES(true),
    /**
     * Classe abre chaves - unívoca
     */
    TK_OPEN_CURLY_BRACKET(true),
    /**
     * Classe fecha chaves - unívoca
     */
    TK_CLOSE_CURLY_BRACKET(true),
    /**
     * Classe virgula - unívoca
     */
    TK_COMMAN(true);
    private boolean univoque;

    /** Construtor TokenClass da Enumeração -
     * Inicializa os atributos da enumeração com os valores recebidos
     * como parâmetro
     * @param  univoque boolean - Valor representando se a classe do token
     * é univoca(true) ou não(false).
     */
    private TokenClass(boolean univoque) {
        this.univoque = univoque;
    }

    /** Método que retorna se a classe do token
     * é univoca ou não.
     * @return boolean - true se univoca ou false se não
     */
    public boolean isUnivoque() {
        return univoque;
    }
}
