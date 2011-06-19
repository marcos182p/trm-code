package trm.syntactic;

import trm.lexical.LexicalAnalyzer;
import trm.lexical.TokenClass;
import trm.syntactic.Derivation;
import trm.syntactic.GLC;
import trm.syntactic.Parser;
import trm.syntactic.Terminal;
import trm.syntactic.Variable;

/**
 *
 * @author Marcos
 */
public class GLCFacotory {
    
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

    
    //operadores aritméticos----------------------------------------------------
    public static final Terminal OP_ADD = new Terminal(TokenClass.TK_ADD);
    public static final Terminal OP_SUB = new Terminal(TokenClass.TK_SUB);
    public static final Terminal OP_MULT = new Terminal(TokenClass.TK_MULT);
    public static final Terminal OP_DIV = new Terminal(TokenClass.TK_DIV);
    public static final Terminal OP_MOD = new Terminal(TokenClass.TK_MOD);
//  Terminal OP_POS = new Terminal(TokenClass.TK_POSITIVE);
//  Terminal OP_NEG = new Terminal(TokenClass.TK_NEGATIVE);

    //operadores relacionais----------------------------------------------------
    public static final Terminal OP_EQUAL = new Terminal(TokenClass.TK_EQUAL);
    public static final Terminal OP_NOT_EQUAL = new Terminal(TokenClass.TK_NOT_EQUAL);
    public static final Terminal OP_MINOR = new Terminal(TokenClass.TK_LESS_THAN);
    public static final Terminal OP_MINOR_OR_EQUAL = new Terminal(TokenClass.TK_LESS_OR_EQUAL);
    public static final Terminal OP_MAJOR = new Terminal(TokenClass.TK_GREATER_THAN);
    public static final Terminal OP_MAJOR_OR_EQUAL = new Terminal(TokenClass.TK_GREATER_OR_EQUAL);
    
    //operadoers logicos--------------------------------------------------------
    public static final Terminal OP_AND = new Terminal(TokenClass.TK_AND);
    public static final Terminal OP_OR = new Terminal(TokenClass.TK_OR);
    public static final Terminal OP_NOT = new Terminal(TokenClass.TK_NOT);
    
    //variaveis-----------------------------------------------------------------
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
    
    public static final Variable E = new Variable("E");
    public static final Variable E_ = new Variable("E'");
    public static final Variable T = new Variable("T");
    public static final Variable T_ = new Variable("T'");
    public static final Variable F = new Variable("F");
    
    public static final Variable ID_ = new Variable("id");
    /**
     * Gera uma gramatica que reconhece expressões aritimeticas, relacionais e
     * logicas
     */
    public static GLC createGLCExpression() {
        GLC glc = new GLC(null);
        
        
        glc.addDerivation(new Derivation(E, T, E_));
        
        glc.addDerivation(new Derivation(E_, OP_ADD, E));
        glc.addDerivation(new Derivation(E_, OP_SUB, E));
        
        glc.addDerivation(new Derivation(E_, OP_EQUAL, E));
        glc.addDerivation(new Derivation(E_, OP_NOT_EQUAL, E));
        glc.addDerivation(new Derivation(E_, OP_MINOR, E));
        glc.addDerivation(new Derivation(E_, OP_MINOR_OR_EQUAL, E));
        glc.addDerivation(new Derivation(E_, OP_MAJOR, E));
        glc.addDerivation(new Derivation(E_, OP_MAJOR_OR_EQUAL, E));
        
        glc.addDerivation(new Derivation(E_, OP_AND, E));
        glc.addDerivation(new Derivation(E_, OP_OR, E));
        
        glc.addDerivation(new Derivation(E_));
                
        glc.addDerivation(new Derivation(T, F, T_));
        glc.addDerivation(new Derivation(T_, OP_MULT, T));
        glc.addDerivation(new Derivation(T_, OP_DIV, T));
        glc.addDerivation(new Derivation(T_, OP_MOD, T));
        glc.addDerivation(new Derivation(T_));

//        glc.addDerivation(new Derivation(ARIT_F,OP_SUB, ARIT_F));//ver isso
//        glc.addDerivation(new Derivation(ARIT_F,OP_ADD, ARIT_F));//ver isso

        glc.addDerivation(new Derivation(F, OPEN_PARENTHESES, E, CLOSE_PARENTHESES));
        glc.addDerivation(new Derivation(F, OP_NOT, F));
        
//        glc.addDerivation(new Derivation(F,INTEGER_CTE));
//        glc.addDerivation(new Derivation(F,ID));
//        glc.addDerivation(new Derivation(F,REAL_CTE));
//        glc.addDerivation(new Derivation(F,BOOLEAN_CTE));
        glc.addDerivation(new Derivation(F, VALUE));
        
        
        glc.addDerivation(new Derivation(CTE, REAL_CTE));
        glc.addDerivation(new Derivation(CTE, INTEGER_CTE));
        glc.addDerivation(new Derivation(CTE, CHARACTER_CTE));
        glc.addDerivation(new Derivation(CTE, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(CTE, STRING_CTE));
        
        glc.addDerivation(new Derivation(VALUE, CTE));
        glc.addDerivation(new Derivation(VALUE, ID, OPT_INDEX));
//        glc.addDerivation(new Derivation(VALUE, FUNCTION_CALL));
        
        //index
        glc.addDerivation(new Derivation(INDEX, OPEN_SQUARE_BRACKET, INDEX_VAL, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(OPT_INDEX, INDEX));
        glc.addDerivation(new Derivation(OPT_INDEX));
        glc.addDerivation(new Derivation(INDEX_VAL, INTEGER_CTE));
        glc.addDerivation(new Derivation(INDEX_VAL, ID));
        glc.addDerivation(new Derivation(INDEX_VAL, E));
        

        
        glc.setInitialElement(E);
        
        return glc;
    }
    
    public static GLC createGLCDeclaration() {
        GLC glc = new GLC(VAR);

        glc.addDerivation(new Derivation(DIM, OPEN_SQUARE_BRACKET, DIM_TYPE, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(DIM_TYPE, ID));
        glc.addDerivation(new Derivation(DIM_TYPE, INT_CTE));
        
        glc.addDerivation(new Derivation(TIPO, REAL));
        glc.addDerivation(new Derivation(TIPO, INTEGER));
        glc.addDerivation(new Derivation(TIPO, CHARACTER));
        glc.addDerivation(new Derivation(TIPO, BOOLEAN));
        
        glc.addDerivation(new Derivation(VAR, ID, VAR_IDS, COLON, TIPO, OPTIONAL_DIM));
        glc.addDerivation(new Derivation(VAR_IDS, COMMA, ID, VAR_IDS));
        glc.addDerivation(new Derivation(VAR_IDS));
        glc.addDerivation(new Derivation(OPTIONAL_DIM, DIM));
        glc.addDerivation(new Derivation(OPTIONAL_DIM));
        
        return glc;
    }
    
    public static GLC createGLCAtribuition() {
        GLC glc = createGLCExpression();
        
        glc.addDerivation(new Derivation(ID_, ID, OPT_INDEX, ATTRIBUTION,
                glc.getInitialElement()));
        
        
        glc.setInitialElement(ID_);
        return glc;
    }
    
    public static GLC createGLCValue() {
        GLC glc = new GLC(VALUE);
        
        glc.addDerivation(new Derivation(CTE, REAL_CTE));
        glc.addDerivation(new Derivation(CTE, INTEGER_CTE));
        glc.addDerivation(new Derivation(CTE, CHARACTER_CTE));
        glc.addDerivation(new Derivation(CTE, BOOLEAN_CTE));
        glc.addDerivation(new Derivation(CTE, STRING_CTE));

        glc.addDerivation(new Derivation(VALUE, CTE));
        glc.addDerivation(new Derivation(VALUE, ID, OPT_INDEX));
//        glc.addDerivation(new Derivation(VALUE, FUNCTION_CALL)); //FIXME isso tem que ser ajeitado

        //index
        glc.addDerivation(new Derivation(INDEX, OPEN_SQUARE_BRACKET, INDEX_VAL, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(OPT_INDEX, INDEX));
        glc.addDerivation(new Derivation(OPT_INDEX));
        glc.addDerivation(new Derivation(INDEX_VAL, INTEGER_CTE));
        glc.addDerivation(new Derivation(INDEX_VAL, ID));
//        glc.addDerivation(new Derivation(INDEX_VAL, E)); considerando que não tem uma expressão aritimetica
        return glc;
    }

    public static void main(String[] args) {
        
        GLC glc = createGLCAtribuition();
        
        LexicalAnalyzer lexical = new LexicalAnalyzer("newtest");
        
//        Token token = lexical.nextToken();
//        lexical.putToken(token);

        Parser instance = new Parser(glc, TokenClass.TK_SEMICOLON);
        instance.parse(lexical);
        instance.parse(lexical);
        instance.parse(lexical);

    }
}
