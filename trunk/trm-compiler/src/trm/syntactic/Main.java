package trm.syntactic;

import trm.lexical.TokenClass;

/**
 *
 * @author TRM
 */
public class Main {
    public static void main(String[] args) {

        GLC glc = new GLC(null);
        
        Terminal OPEN_SQUARE_BRACKET = new Terminal(TokenClass.TK_OPEN_SQUARE_BRACKET);

        Terminal CLOSE_SQUARE_BRACKET = new Terminal(TokenClass.TK_CLOSE_SQUARE_BRACKET);

        Terminal COMMAN = new Terminal(TokenClass.TK_COMMAN);

        Terminal ID = new Terminal(TokenClass.TK_ID);

        Terminal INT_CTE = new Terminal(TokenClass.TK_INTEGER_CTE);

        Terminal REAL = new Terminal(TokenClass.TK_REAL);

        Terminal INTEGER = new Terminal(TokenClass.TK_INTEGER);

        Terminal CHARACTER = new Terminal(TokenClass.TK_CHARACTER);

        Terminal BOOLEAN = new Terminal(TokenClass.TK_BOOLEAN);

        Terminal VOID = new Terminal(TokenClass.TK_VOID);




        Variable TIPO = new Variable("tipo");

        glc.addDerivation(new Derivation(TIPO, REAL));
        glc.addDerivation(new Derivation(TIPO, INTEGER));
        glc.addDerivation(new Derivation(TIPO, CHARACTER));
        glc.addDerivation(new Derivation(TIPO, BOOLEAN));
        glc.addDerivation(new Derivation(TIPO, VOID));

        Variable DIM = new Variable("dim");
        Variable VAR = new Variable("var");



        glc.addDerivation(new Derivation(DIM, OPEN_SQUARE_BRACKET, ID, CLOSE_SQUARE_BRACKET));
        glc.addDerivation(new Derivation(DIM, OPEN_SQUARE_BRACKET, INT_CTE, CLOSE_SQUARE_BRACKET));

        glc.addDerivation(new Derivation(VAR, ID, COMMAN, TIPO));



    }
}
