package trm.syntactic;

import trm.lexical.LexicalAnalyzer;
import trm.lexical.TokenClass;

/**
 *
 * @author TRM
 */
@Deprecated
public class Main {

    public static void main(String[] args) {

        GLC glc = new GLC(null);

        Terminal OPEN_SQUARE_BRACKET = new Terminal(TokenClass.TK_OPEN_SQUARE_BRACKET);

        Terminal CLOSE_SQUARE_BRACKET = new Terminal(TokenClass.TK_CLOSE_SQUARE_BRACKET);

        Terminal OPEN_CURLY_BRACKET = new Terminal(TokenClass.TK_OPEN_CURLY_BRACKET);

        Terminal CLOSE_CURLY_BRACKET = new Terminal(TokenClass.TK_OPEN_CURLY_BRACKET);

        Terminal OPEN_PARENTHESES = new Terminal(TokenClass.TK_OPEN_PARENTHESES);

        Terminal CLOSE_PARENTHESES = new Terminal(TokenClass.TK_CLOSE_PARENTHESES);

        Terminal COMMA = new Terminal(TokenClass.TK_COMMA);

        Terminal COLON = new Terminal(TokenClass.TK_COLON);

        Terminal SEMICOLON = new Terminal(TokenClass.TK_SEMICOLON);

        Terminal ID = new Terminal(TokenClass.TK_ID);

        Terminal INT_CTE = new Terminal(TokenClass.TK_INTEGER_CTE);

        Terminal REAL = new Terminal(TokenClass.TK_REAL);

        Terminal INTEGER = new Terminal(TokenClass.TK_INTEGER);

        Terminal CHARACTER = new Terminal(TokenClass.TK_CHARACTER);

        Terminal BOOLEAN = new Terminal(TokenClass.TK_BOOLEAN);

        Terminal VOID = new Terminal(TokenClass.TK_VOID);

        Terminal RETURN = new Terminal(TokenClass.TK_RETURN);

        Terminal INTEGER_CTE = new Terminal(TokenClass.TK_INTEGER_CTE);

        Terminal REAL_CTE = new Terminal(TokenClass.TK_REAL_CTE);

        Terminal BOOLEAN_CTE = new Terminal(TokenClass.TK_BOOLEAN_CTE);

        Terminal CHARACTER_CTE = new Terminal(TokenClass.TK_CHARACTER_CTE);

        Terminal STRING_CTE = new Terminal(TokenClass.TK_STRING_CTE);

        Terminal ATTRIBUTION = new Terminal(TokenClass.TK_ATTRIBUTION);

        Terminal IF = new Terminal(TokenClass.TK_IF);

        Terminal ELSE = new Terminal(TokenClass.TK_ELSE);

        Terminal WHILE = new Terminal(TokenClass.TK_WHILE);

        Terminal FOR = new Terminal(TokenClass.TK_FOR);

        Terminal IN = new Terminal(TokenClass.TK_IN);

        /*----------------------------OPERAÇÕES-------------------------------*/
        //logicas
        Terminal OP_AND = new Terminal(TokenClass.TK_AND);
        Terminal OP_OR = new Terminal(TokenClass.TK_OR);
        Terminal OP_NOT = new Terminal(TokenClass.TK_NOT);
        //aritméticas
        Terminal OP_ADD = new Terminal(TokenClass.TK_ADD);
        Terminal OP_SUB = new Terminal(TokenClass.TK_SUB);
        Terminal OP_MULT = new Terminal(TokenClass.TK_MULT);
        Terminal OP_DIV = new Terminal(TokenClass.TK_DIV);
        Terminal OP_MOD = new Terminal(TokenClass.TK_MOD);
        Terminal OP_POS = new Terminal(TokenClass.TK_POSITIVE);
        Terminal OP_NEG = new Terminal(TokenClass.TK_NEGATIVE);
        //relacionais
        Terminal OP_EQUAL = new Terminal(TokenClass.TK_EQUAL);
        Terminal OP_NOT_EQUAL = new Terminal(TokenClass.TK_NOT_EQUAL);
        Terminal OP_MINOR = new Terminal(TokenClass.TK_LESS_THAN);
        Terminal OP_MINOR_OR_EQUAL = new Terminal(TokenClass.TK_LESS_OR_EQUAL);
        Terminal OP_MAJOR = new Terminal(TokenClass.TK_GREATER_THAN);
        Terminal OP_MAJOR_OR_EQUAL = new Terminal(TokenClass.TK_GREATER_OR_EQUAL);

        Variable CMD = new Variable("CMD");
        Variable CTE = new Variable("cte");
        Variable TIPO = new Variable("tipo");

        Variable DIM = new Variable("dim");
        Variable DIM_TYPE = new Variable("dim_type");
        Variable OPTIONAL_DIM = new Variable("[dim]");
        Variable VAR = new Variable("var");
        Variable VAR_IDS = new Variable("[,ids]");
        Variable FUNC = new Variable("func");

        Variable PARAM = new Variable("param");
        Variable OPTIONAL_PARAM = new Variable("[param]");
        Variable OPTIONAL_RETURN = new Variable("[return]");

        Variable VALUE = new Variable("value");
        Variable INDEX = new Variable("Index");
        Variable INDEX_VAL = new Variable("Index_val");
        Variable OPT_INDEX = new Variable("[index]");
        Variable ATTRIBUTION_OP = new Variable("[attribution]");
        Variable FUNCTION_CALL = new Variable("func_call");
        Variable INSTRUCTION = new Variable("instruction");
        Variable INSTRUCTION_ = new Variable("instruction'");

        Variable INSTR_COND = new Variable("if");
        Variable INSTR_ELSE = new Variable("else");
        Variable OPTIONAL_ELSE = new Variable("[else]");
        Variable REPEAT_ITER = new Variable("for");
        Variable OPTIONAL_JUMP = new Variable("[jump]");
        Variable REPEAT_COND = new Variable("while");

        Variable EXPR_LOG = new Variable("expr_log");
        Variable MAIOR_PREC_LOG = new Variable("maior_prec_log");
        Variable MENOR_PREC_LOG = new Variable("menor_prec_log");
        Variable OPTIONAL_MENOR_PREC_LOG = new Variable("[menor_prec_log]");
        Variable MAIOR_PREC_OP_LOG = new Variable("maior_prec_op_log");
        Variable OPTIONAL_MAIOR_PREC_OP_LOG = new Variable("[maior_prec_op_log]");
        Variable MAIOR_PREC_UN_LOG = new Variable("maior_prec_un_log");
        Variable AUX_EXPR_LOG = new Variable("(expr_log|expr_rel)");

        Variable EXPR_REL = new Variable("expr_rel");
        Variable FIRST_ELEM = new Variable("first_elem");
        Variable OPERATION = new Variable("operation");
        Variable OPERATION_OPERATORS = new Variable("operators");

        Variable EXPR_ARIT = new Variable("expr_arit");
        Variable MAIOR_PREC_ARIT = new Variable("maior_prec_arit");
        Variable MENOR_PREC_ARIT = new Variable("menor_prec_arit");
        Variable MENOR_PREC_ARIT_OPERATORS = new Variable("menor_prec_arit_op");
        Variable OPTIONAL_MENOR_PREC_ARIT = new Variable("[menor_prec_arit]");
        Variable MAIOR_PREC_OP_ARIT = new Variable("maior_prec_op_arit");
        Variable MAIOR_PREC_OP_ARIT_OPERATORS = new Variable("maior_prec_op_arit_op");
        Variable OPTIONAL_MAIOR_PREC_OP_ARIT = new Variable("[maior_prec_op_arit]");
        Variable MAIOR_PREC_UN_ARIT = new Variable("maior_prec_un_arit");
        Variable MAIOR_PREC_UN_ARIT_OPERATORS = new Variable("maior_prec_un_arit_op");

        Variable EXPR = new Variable("expr");


        /*---------------------------DECLARAÇÃO-------------------------------*/

        glc.addDerivation(new Derivation(CTE, REAL_CTE));
        glc.addDerivation(new Derivation(CTE, INTEGER_CTE));
        glc.addDerivation(new Derivation(CTE, CHARACTER_CTE));
        glc.addDerivation(new Derivation(CTE, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(CTE, STRING_CTE));


        glc.addDerivation(new Derivation(TIPO, REAL));
        glc.addDerivation(new Derivation(TIPO, INTEGER));
        glc.addDerivation(new Derivation(TIPO, CHARACTER));
        glc.addDerivation(new Derivation(TIPO, BOOLEAN));


        //Dim
        glc.addDerivation(new Derivation(DIM, OPEN_SQUARE_BRACKET, DIM_TYPE, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(DIM_TYPE, ID));
        glc.addDerivation(new Derivation(DIM_TYPE, INT_CTE));

        //Var
        glc.addDerivation(new Derivation(VAR, ID, VAR_IDS, COLON, TIPO, OPTIONAL_DIM));
        glc.addDerivation(new Derivation(VAR_IDS, COMMA, ID, VAR_IDS));
        glc.addDerivation(new Derivation(VAR_IDS));
        glc.addDerivation(new Derivation(OPTIONAL_DIM, DIM));
        glc.addDerivation(new Derivation(OPTIONAL_DIM));

        //Param
        glc.addDerivation(new Derivation(PARAM, VAR, VAR_IDS));

        //Func
        glc.addDerivation(new Derivation(FUNC, ID, OPEN_PARENTHESES, OPTIONAL_PARAM, CLOSE_PARENTHESES, COLON, TIPO,
                OPEN_CURLY_BRACKET, CMD, RETURN, CLOSE_CURLY_BRACKET));

        glc.addDerivation(new Derivation(FUNC, ID, OPEN_PARENTHESES, OPTIONAL_PARAM, CLOSE_PARENTHESES, COLON, VOID,
                OPEN_CURLY_BRACKET, CMD, OPTIONAL_RETURN, CLOSE_CURLY_BRACKET));

        glc.addDerivation(new Derivation(OPTIONAL_RETURN, RETURN));
        glc.addDerivation(new Derivation(OPTIONAL_RETURN));

        /*---------------------------INSTRUÇÕES-------------------------------*/

        //val
        glc.addDerivation(new Derivation(VALUE, EXPR));
        glc.addDerivation(new Derivation(VALUE, CTE));
        glc.addDerivation(new Derivation(VALUE, ID, OPT_INDEX));
        glc.addDerivation(new Derivation(VALUE, FUNCTION_CALL));
        //index
        glc.addDerivation(new Derivation(INDEX, OPEN_SQUARE_BRACKET, INDEX_VAL, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(OPT_INDEX, INDEX));
        glc.addDerivation(new Derivation(OPT_INDEX));
        glc.addDerivation(new Derivation(INDEX_VAL, INTEGER_CTE));
        glc.addDerivation(new Derivation(INDEX_VAL, ID));
        glc.addDerivation(new Derivation(INDEX_VAL, EXPR_ARIT));

        //atrib
        glc.addDerivation(new Derivation(ATTRIBUTION_OP, ID, OPT_INDEX, ATTRIBUTION, VALUE));

        //func_call
        glc.addDerivation(new Derivation(FUNCTION_CALL, ID, OPEN_PARENTHESES, OPTIONAL_PARAM, CLOSE_PARENTHESES));

        //instr
        glc.addDerivation(new Derivation(INSTRUCTION, INSTRUCTION_, SEMICOLON));
        glc.addDerivation(new Derivation(INSTRUCTION_, ATTRIBUTION_OP));
        glc.addDerivation(new Derivation(INSTRUCTION_, VAR));
        glc.addDerivation(new Derivation(INSTRUCTION_, FUNCTION_CALL));

        /*-----------------------------BLOCOS---------------------------------*/




        glc.addDerivation(new Derivation(INSTR_COND, IF, OPEN_PARENTHESES, EXPR_LOG, CLOSE_PARENTHESES,
                OPEN_CURLY_BRACKET, CMD, CLOSE_CURLY_BRACKET, OPTIONAL_ELSE));

        glc.addDerivation(new Derivation(INSTR_ELSE, ELSE, OPEN_CURLY_BRACKET, CMD, CLOSE_CURLY_BRACKET));
        glc.addDerivation(new Derivation(OPTIONAL_ELSE, ELSE));
        glc.addDerivation(new Derivation(OPTIONAL_ELSE));

        glc.addDerivation(new Derivation(REPEAT_ITER, FOR, OPEN_PARENTHESES, ID, IN, EXPR_ARIT, COLON, EXPR_ARIT, OPTIONAL_JUMP, CLOSE_PARENTHESES,
                OPEN_CURLY_BRACKET, CMD, CLOSE_CURLY_BRACKET));

        glc.addDerivation(new Derivation(OPTIONAL_JUMP, COLON, EXPR_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_JUMP));

        glc.addDerivation(new Derivation(REPEAT_COND, WHILE, OPEN_PARENTHESES, EXPR_LOG, CLOSE_PARENTHESES,
                OPEN_CURLY_BRACKET, CMD, CLOSE_CURLY_BRACKET));

        /*---------------------------Expressões------------------------------*/

        //logica


        glc.addDerivation(new Derivation(EXPR_LOG, MAIOR_PREC_LOG, OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(EXPR_LOG, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(MENOR_PREC_LOG, OP_OR, MAIOR_PREC_LOG, OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_LOG, MAIOR_PREC_UN_LOG, OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_LOG, OP_AND, MAIOR_PREC_UN_LOG, OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, OP_NOT, MAIOR_PREC_UN_LOG));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, OPEN_PARENTHESES, AUX_EXPR_LOG, CLOSE_PARENTHESES));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_LOG, ID));

        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_LOG, MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_LOG, MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_LOG));
        glc.addDerivation(new Derivation(AUX_EXPR_LOG, EXPR_LOG));
        glc.addDerivation(new Derivation(AUX_EXPR_LOG, EXPR_REL));

        //relacional


        glc.addDerivation(new Derivation(EXPR_REL, FIRST_ELEM, OPERATION));
        glc.addDerivation(new Derivation(OPERATION, OPERATION_OPERATORS, FIRST_ELEM));
        glc.addDerivation(new Derivation(FIRST_ELEM, ID));
        glc.addDerivation(new Derivation(FIRST_ELEM, EXPR_ARIT));

        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MINOR));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MINOR_OR_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MAJOR));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_MAJOR_OR_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_EQUAL));
        glc.addDerivation(new Derivation(OPERATION_OPERATORS, OP_NOT_EQUAL));

        //aritmético
//        EXPR_ARIT::= maior_prec_arit, [menor_prec_arit] | int_cte | real_cte
//        menor_prec_arit ::= (op_sum | op_sub), maior_prec_arit, [menor_prec_arit]
//        maior_prec_arit ::= maior_prec_un_arit, [maior_prec_op_arit]
//        maior_prec_op_arit ::= (op_mult | op_div| op_mod), maior_prec_un_arit, [maior_prec_op_arit]
//        maior_prec_un_arit ::= (op_pos | op_neg),  maior_prec_un_arit | “(“, EXPR_ARIT, “)” | id




        glc.addDerivation(new Derivation(EXPR_ARIT, MAIOR_PREC_ARIT, OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(EXPR_ARIT, INT_CTE));
        glc.addDerivation(new Derivation(EXPR_ARIT, REAL_CTE));
        glc.addDerivation(new Derivation(MENOR_PREC_ARIT, MENOR_PREC_ARIT_OPERATORS, MAIOR_PREC_ARIT, OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_ARIT, MAIOR_PREC_UN_ARIT, OPTIONAL_MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT, MAIOR_PREC_OP_ARIT_OPERATORS, MAIOR_PREC_UN_ARIT, OPTIONAL_MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT, MAIOR_PREC_UN_ARIT_OPERATORS, MAIOR_PREC_UN_ARIT));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT, OPEN_PARENTHESES, EXPR_ARIT, CLOSE_PARENTHESES));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT, ID));

        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_ARIT, MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MENOR_PREC_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_ARIT, MAIOR_PREC_OP_ARIT));
        glc.addDerivation(new Derivation(OPTIONAL_MAIOR_PREC_OP_ARIT));

        glc.addDerivation(new Derivation(MENOR_PREC_ARIT_OPERATORS, OP_ADD));
        glc.addDerivation(new Derivation(MENOR_PREC_ARIT_OPERATORS, OP_SUB));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_MULT));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_DIV));
        glc.addDerivation(new Derivation(MAIOR_PREC_OP_ARIT_OPERATORS, OP_MOD));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT_OPERATORS, OP_POS));
        glc.addDerivation(new Derivation(MAIOR_PREC_UN_ARIT_OPERATORS, OP_NEG));

        glc.addDerivation(new Derivation(EXPR, EXPR_LOG));
        glc.addDerivation(new Derivation(EXPR, EXPR_ARIT));
        glc.addDerivation(new Derivation(EXPR, EXPR_REL));

        /*-----------------------------Comando--------------------------------*/


        glc.addDerivation(new Derivation(CMD, INSTRUCTION));
        glc.addDerivation(new Derivation(CMD, INSTR_COND));
        glc.addDerivation(new Derivation(CMD, REPEAT_ITER));
        glc.addDerivation(new Derivation(CMD, REPEAT_COND));

        glc.setInitialElement(CMD);
//        glc.initGLC();
        PreditiveTable table = TableGenerator.createPreditiveTable(glc);


        LexicalAnalyzer lexical = new LexicalAnalyzer("x_test");

        GLCAnalyser instance = new GLCAnalyser(glc);
        System.out.println(table.isAmbiguous());
        instance.analysis(lexical);

    }
}
