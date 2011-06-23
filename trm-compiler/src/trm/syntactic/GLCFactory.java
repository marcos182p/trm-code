package trm.syntactic;

import trm.lexical.TokenClass;

/** Classe GLCFactory - Classe responsável por
 * criar uma GLC que reconhecerá a linguagem
 * @author TRM 
 * @version 0.99
 */
public class GLCFactory {

    //Instânciação dos simbolos terminais da gramática
    //Tokens finais-------------------------------------------------------------
    public static final Terminal OPEN_SQUARE_BRACKET = new Terminal(TokenClass.TK_OPEN_SQUARE_BRACKET);
    public static final Terminal CLOSE_SQUARE_BRACKET = new Terminal(TokenClass.TK_CLOSE_SQUARE_BRACKET);
    public static final Terminal OPEN_CURLY_BRACKET = new Terminal(TokenClass.TK_OPEN_CURLY_BRACKET);
    public static final Terminal CLOSE_CURLY_BRACKET = new Terminal(TokenClass.TK_OPEN_CURLY_BRACKET);
    public static final Terminal OPEN_PARENTHESES = new Terminal(TokenClass.TK_OPEN_PARENTHESES);
    public static final Terminal CLOSE_PARENTHESES = new Terminal(TokenClass.TK_CLOSE_PARENTHESES);
    public static final Terminal COMMA = new Terminal(TokenClass.TK_COMMA);
    public static final Terminal COLON = new Terminal(TokenClass.TK_COLON);
    public static final Terminal SEMICOLON = new Terminal(TokenClass.TK_SEMICOLON);
    public static final Terminal ID = new Terminal(TokenClass.TK_ID);
    public static final Terminal INT_CTE = new Terminal(TokenClass.TK_INTEGER_CTE);
    public static final Terminal REAL = new Terminal(TokenClass.TK_REAL);
    public static final Terminal INTEGER = new Terminal(TokenClass.TK_INTEGER);
    public static final Terminal CHARACTER = new Terminal(TokenClass.TK_CHARACTER);
    public static final Terminal BOOLEAN = new Terminal(TokenClass.TK_BOOLEAN);
    public static final Terminal VOID = new Terminal(TokenClass.TK_VOID);
    public static final Terminal RETURN = new Terminal(TokenClass.TK_RETURN);
    public static final Terminal INTEGER_CTE = new Terminal(TokenClass.TK_INTEGER_CTE);
    public static final Terminal REAL_CTE = new Terminal(TokenClass.TK_REAL_CTE);
    public static final Terminal BOOLEAN_CTE = new Terminal(TokenClass.TK_BOOLEAN_CTE);
    public static final Terminal CHARACTER_CTE = new Terminal(TokenClass.TK_CHARACTER_CTE);
    public static final Terminal STRING_CTE = new Terminal(TokenClass.TK_STRING_CTE);
    public static final Terminal ATTRIBUTION = new Terminal(TokenClass.TK_ATTRIBUTION);
    public static final Terminal IF = new Terminal(TokenClass.TK_IF);
    public static final Terminal ELSE = new Terminal(TokenClass.TK_ELSE);
    public static final Terminal WHILE = new Terminal(TokenClass.TK_WHILE);
    public static final Terminal FOR = new Terminal(TokenClass.TK_FOR);
    public static final Terminal IN = new Terminal(TokenClass.TK_IN);
    //Operadores aritméticos----------------------------------------------------
    public static final Terminal OP_ADD = new Terminal(TokenClass.TK_ADD);
    public static final Terminal OP_SUB = new Terminal(TokenClass.TK_SUB);
    public static final Terminal OP_MULT = new Terminal(TokenClass.TK_MULT);
    public static final Terminal OP_DIV = new Terminal(TokenClass.TK_DIV);
    public static final Terminal OP_MOD = new Terminal(TokenClass.TK_MOD);
    //Operadores relacionais----------------------------------------------------
    public static final Terminal OP_EQUAL = new Terminal(TokenClass.TK_EQUAL);
    public static final Terminal OP_NOT_EQUAL = new Terminal(TokenClass.TK_NOT_EQUAL);
    public static final Terminal OP_MINOR = new Terminal(TokenClass.TK_LESS_THAN);
    public static final Terminal OP_MINOR_OR_EQUAL = new Terminal(TokenClass.TK_LESS_OR_EQUAL);
    public static final Terminal OP_MAJOR = new Terminal(TokenClass.TK_GREATER_THAN);
    public static final Terminal OP_MAJOR_OR_EQUAL = new Terminal(TokenClass.TK_GREATER_OR_EQUAL);
    //Operadoers lógicos--------------------------------------------------------
    public static final Terminal OP_AND = new Terminal(TokenClass.TK_AND);
    public static final Terminal OP_OR = new Terminal(TokenClass.TK_OR);
    public static final Terminal OP_NOT = new Terminal(TokenClass.TK_NOT);
    //Variáveis-----------------------------------------------------------------
    public static final Variable CMD = new Variable("CMD");
    public static final Variable CTE = new Variable("cte");
    public static final Variable TIPO = new Variable("tipo");
    public static final Variable DIM = new Variable("dim");
    public static final Variable DIM_TYPE = new Variable("dim_type");
    public static final Variable OPTIONAL_DIM = new Variable("[dim]");
    public static final Variable VAR = new Variable("var");
    public static final Variable VAR_IDS = new Variable("[,ids]");
    public static final Variable FUNC = new Variable("func");
    public static final Variable PARAM = new Variable("param");
    public static final Variable OPTIONAL_PARAM = new Variable("[param]");
    public static final Variable OPTIONAL_DECLAR = new Variable("[declaration]");
    public static final Variable OPTIONAL_DECLAR_RETURN = new Variable("optional_declaration_return");
    public static final Variable OPTIONAL_RETURN = new Variable("[return]");
    public static final Variable VALUE = new Variable("value");
    public static final Variable INDEX = new Variable("Index");
    public static final Variable INDEX_VAL = new Variable("Index_val");
    public static final Variable OPT_INDEX = new Variable("[index]");
    public static final Variable ATTRIBUTION_OP = new Variable("[attribution]");
    public static final Variable FUNCTION_CALL = new Variable("func_call");
    public static final Variable INSTRUCTION = new Variable("instruction");
    public static final Variable INSTRUCTION_ = new Variable("instruction'");
    public static final Variable INSTR_COND = new Variable("if");
    public static final Variable INSTR_ELSE = new Variable("else");
    public static final Variable OPTIONAL_ELSE = new Variable("[else]");
    public static final Variable REPEAT_ITER = new Variable("for");
    public static final Variable OPTIONAL_JUMP = new Variable("[jump]");
    public static final Variable REPEAT_COND = new Variable("while");
    public static final Variable EXPR_LOG = new Variable("expr_log");
    public static final Variable MAIOR_PREC_LOG = new Variable("maior_prec_log");
    public static final Variable MENOR_PREC_LOG = new Variable("menor_prec_log");
    public static final Variable OPTIONAL_MENOR_PREC_LOG = new Variable("[menor_prec_log]");
    public static final Variable MAIOR_PREC_OP_LOG = new Variable("maior_prec_op_log");
    public static final Variable OPTIONAL_MAIOR_PREC_OP_LOG = new Variable("[maior_prec_op_log]");
    public static final Variable MAIOR_PREC_UN_LOG = new Variable("maior_prec_un_log");
    public static final Variable AUX_EXPR_LOG = new Variable("(expr_log|expr_rel)");
    public static final Variable EXPR_REL = new Variable("expr_rel");
    public static final Variable FIRST_ELEM = new Variable("first_elem");
    public static final Variable OPERATION = new Variable("operation");
    public static final Variable OPERATION_OPERATORS = new Variable("operators");
    public static final Variable EXPR_ARIT = new Variable("expr_arit");
    public static final Variable MAIOR_PREC_ARIT = new Variable("maior_prec_arit");
    public static final Variable MENOR_PREC_ARIT = new Variable("menor_prec_arit");
    public static final Variable MENOR_PREC_ARIT_OPERATORS = new Variable("menor_prec_arit_op");
    public static final Variable OPTIONAL_MENOR_PREC_ARIT = new Variable("[menor_prec_arit]");
    public static final Variable MAIOR_PREC_OP_ARIT = new Variable("maior_prec_op_arit");
    public static final Variable MAIOR_PREC_OP_ARIT_OPERATORS = new Variable("maior_prec_op_arit_op");
    public static final Variable OPTIONAL_MAIOR_PREC_OP_ARIT = new Variable("[maior_prec_op_arit]");
    public static final Variable MAIOR_PREC_UN_ARIT = new Variable("maior_prec_un_arit");
    public static final Variable MAIOR_PREC_UN_ARIT_OPERATORS = new Variable("maior_prec_un_arit_op");
    public static final Variable EXPR = new Variable("expr");
    public static final Variable ID_ = new Variable("id");

    /** Método que gera uma GLC que reconhece expressões aritiméticas,
     * relacionais e lógicas
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCExpression() {

        //Instância
        GLC glc = createGLCValue();

        //Seta o elemento inicial
        glc.setInitialElement(EXPR);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(EXPR, EXPR_LOG));
        glc.addDerivation(new Derivation(EXPR_LOG, MAIOR_PREC_LOG, OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(EXPR_LOG, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(MENOR_PREC_LOG, OP_OR, MAIOR_PREC_LOG, OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_LOG, MAIOR_PREC_UN_LOG, OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_LOG, OP_AND, MAIOR_PREC_UN_LOG, OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, OP_NOT, MAIOR_PREC_UN_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, EXPR_REL));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, BOOLEAN_CTE));
        glc.removeDerivation(new Derivation(CTE, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_LOG, MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_LOG, MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(EXPR_REL, FIRST_ELEM, OPERATION));
        glc.addDerivation(new Derivation(OPERATION, OPERATION_OPERATORS, FIRST_ELEM));
        glc.addDerivation(new Derivation(OPERATION));
        glc.addDerivation(new Derivation(FIRST_ELEM, EXPR_ARIT));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MINOR));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MINOR_OR_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MAJOR));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MAJOR_OR_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_NOT_EQUAL));
        glc.addDerivation(new Derivation(EXPR_ARIT, MAIOR_PREC_ARIT, OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(MENOR_PREC_ARIT, MENOR_PREC_ARIT_OPERATORS, MAIOR_PREC_ARIT, OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_ARIT, MAIOR_PREC_UN_ARIT, OPTIONAL_MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT, MAIOR_PREC_OP_ARIT_OPERATORS, MAIOR_PREC_UN_ARIT, OPTIONAL_MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT, OPEN_PARENTHESES, EXPR_ARIT, CLOSE_PARENTHESES));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT, VALUE));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_ARIT, MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_ARIT, MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(MENOR_PREC_ARIT_OPERATORS, OP_ADD));
        glc.addDerivation(new Derivation(MENOR_PREC_ARIT_OPERATORS, OP_SUB));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_MULT));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_DIV));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_MOD));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece declarações
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCDeclaration() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(VAR);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(DIM, OPEN_SQUARE_BRACKET, DIM_TYPE, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(DIM_TYPE, EXPR));
        glc.addDerivation(new Derivation(DIM_TYPE));
        glc.addDerivation(new Derivation(TIPO, REAL));
        glc.addDerivation(new Derivation(TIPO, INTEGER));
        glc.addDerivation(new Derivation(TIPO, CHARACTER));
        glc.addDerivation(new Derivation(TIPO, BOOLEAN));
        glc.addDerivation(new Derivation(VAR, ID, VAR_IDS, COLON, TIPO, OPTIONAL_DIM));
        glc.addDerivation(new Derivation(VAR_IDS, COMMA, ID, VAR_IDS));
        glc.addDerivation(new Derivation(VAR_IDS));
        glc.addDerivation(new Derivation(OPTIONAL_DIM, DIM));
        glc.addDerivation(new Derivation(OPTIONAL_DIM));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece atribuições
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCAtribuition() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(ID_);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(ID_, ID, OPT_INDEX, ATTRIBUTION,
                glc.getInitialElement()));


        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece valores
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCValue() {

        //Instância
        GLC glc = new GLC(VALUE);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(CTE, REAL_CTE));
        glc.addDerivation(new Derivation(CTE, INTEGER_CTE));
        glc.addDerivation(new Derivation(CTE, CHARACTER_CTE));
        glc.addDerivation(new Derivation(CTE, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(CTE, STRING_CTE));
        glc.addDerivation(new Derivation(VALUE, CTE));
        glc.addDerivation(new Derivation(VALUE, ID, OPT_INDEX));


        //chamada de função-----------------------------------------------------
        glc.addDerivation(new Derivation(OPT_INDEX, FUNCTION_CALL));
        glc.addDerivation(new Derivation(FUNCTION_CALL, OPEN_PARENTHESES,
                OPTIONAL_PARAM, CLOSE_PARENTHESES));

        Variable temp = new Variable("optional_paran'");

        glc.addDerivation(new Derivation(OPTIONAL_PARAM, EXPR, temp));
        glc.addDerivation(new Derivation(OPTIONAL_PARAM));
        glc.addDerivation(new Derivation(temp, COMMA, OPTIONAL_PARAM));
        glc.addDerivation(new Derivation(temp));
        //----------------------------------------------------------------------

        //index
        glc.addDerivation(new Derivation(INDEX, OPEN_SQUARE_BRACKET, INDEX_VAL, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(OPT_INDEX, INDEX));
        glc.addDerivation(new Derivation(OPT_INDEX));
        glc.addDerivation(new Derivation(INDEX_VAL, EXPR)); //considerando que não tem uma expressão aritimetica

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece chamadas de função
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCFunctionCall() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(FUNCTION_CALL);

        //Retorna a nova instância da GLC
        return glc;

    }

    /** Método que gera uma GLC que reconhece declaração de funções
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCFuntionDeclaration() {

        //Instância
        GLC glc = createGLCDeclaration();

        //Seta o elemento inicial
        glc.setInitialElement(FUNC);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(FUNC, ID, OPEN_PARENTHESES,
                OPTIONAL_DECLAR, CLOSE_PARENTHESES, OPTIONAL_DECLAR_RETURN,
                OPEN_CURLY_BRACKET));

        Variable VARS = new Variable("vars");

        glc.addDerivation(new Derivation(OPTIONAL_DECLAR));
        glc.addDerivation(new Derivation(OPTIONAL_DECLAR, PARAM));
        glc.addDerivation(new Derivation(PARAM, VAR, VARS));
        glc.addDerivation(new Derivation(VARS));
        glc.addDerivation(new Derivation(VARS, COMMA, PARAM));
        glc.addDerivation(new Derivation(TIPO, VOID));
        glc.addDerivation(new Derivation(OPTIONAL_DECLAR_RETURN, COLON, TIPO));
        glc.addDerivation(new Derivation(OPTIONAL_DECLAR_RETURN));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece o comando for
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCFor() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(REPEAT_ITER);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(REPEAT_ITER, FOR, OPEN_PARENTHESES,
                ID, IN, EXPR, OPTIONAL_JUMP, CLOSE_PARENTHESES,
                OPEN_CURLY_BRACKET));
        glc.addDerivation(new Derivation(OPTIONAL_JUMP, COLON, EXPR));
        glc.addDerivation(new Derivation(OPTIONAL_JUMP));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece o comando while
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCWhile() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(REPEAT_COND);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(REPEAT_COND, WHILE, OPEN_PARENTHESES,
                EXPR, CLOSE_PARENTHESES, OPEN_CURLY_BRACKET));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece o comando if
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCIf() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(INSTR_COND);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(INSTR_COND, IF, OPEN_PARENTHESES, EXPR,
                CLOSE_PARENTHESES, OPEN_CURLY_BRACKET));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece o comando else
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCElse() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(INSTR_ELSE);

        //Adiciona as derivações
        glc.addDerivation(new Derivation(INSTR_ELSE, ELSE, OPEN_CURLY_BRACKET));

        //Retorna a nova instância da GLC
        return glc;
    }

    /** Método que gera uma GLC que reconhece o return
     * @return GLC - Instância da gramática
     */
    public static GLC createGLCReturn() {

        //Instância
        GLC glc = createGLCExpression();

        //Seta o elemento inicial
        glc.setInitialElement(OPTIONAL_RETURN);

        Variable temp = new Variable("optional_expression");

        //Adiciona as derivações
        glc.addDerivation(new Derivation(OPTIONAL_RETURN, RETURN, temp));
        glc.addDerivation(new Derivation(OPTIONAL_RETURN));
        glc.addDerivation(new Derivation(temp, EXPR));
        glc.addDerivation(new Derivation(temp));

        //Retorna a nova instância da GLC
        return glc;

    }
}
