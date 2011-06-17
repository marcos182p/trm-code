/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.syntactic.test;

import java.util.Set;
import trm.syntactic.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.TokenClass;
import trm.syntactic.Variable;

/**
 *
 * @author rafanet
 */
public class ParserTest {

    private PreditiveTable table;
    private ILexical lexical;
    private Variable initial;

    private GLC glc;

    public ParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Variable E = new Variable("E");
        initial = E;
        Variable E_ = new Variable("E'");
        Variable T= new Variable("T");
        Variable T_ = new Variable("T'");
        Variable F = new Variable("F");

        Terminal id = new Terminal(TokenClass.TK_ID);

        Terminal or = new Terminal(TokenClass.TK_OR);

        Terminal and = new Terminal(TokenClass.TK_AND);

        Terminal not = new Terminal(TokenClass.TK_NOT);

        Terminal eof = new Terminal(TokenClass.TK_EOF);

        table = new PreditiveTable();
        glc = new GLC(initial);

        table.putEntryTable(id, new Derivation(E, T, E_));
        glc.addDerivation(new Derivation(E, T, E_));

        table.putEntryTable(id, new Derivation(T, F, T_));
        glc.addDerivation(new Derivation(T, F, T_));

        table.putEntryTable(id, new Derivation(F, id));
        glc.addDerivation(new Derivation(F, id));


        table.putEntryTable(or, new Derivation(E_, or, T, E_));
        glc.addDerivation(new Derivation(E_, or, T, E_));
        table.putEntryTable(or, new Derivation(T_));
        glc.addDerivation(new Derivation(T_));

        table.putEntryTable(and, new Derivation(T_, and, F, T_));
        glc.addDerivation(new Derivation(T_, and, F, T_));

        table.putEntryTable(not, new Derivation(E, T, E_));
        glc.addDerivation(new Derivation(E, T, E_));
        table.putEntryTable(not, new Derivation(T, F, T_));
        glc.addDerivation(new Derivation(T, F, T_));
        table.putEntryTable(not, new Derivation(F, not, F));
        glc.addDerivation(new Derivation(F, not, F));

        table.putEntryTable(eof, new Derivation(E_));
        glc.addDerivation(new Derivation(E_));
        table.putEntryTable(eof, new Derivation(T_));
        glc.addDerivation(new Derivation(T_));

//        table = TableGenerator.createPreditiveTable(glc);
//        table.printTable();
        lexical = new LexicalAnalyzer("syntactic_test");

        System.out.println("follow");
        glc.initGLC();
        Set<Terminal> follow = glc.follow(T_);

        System.out.println("<>" + follow.size());
        for (Terminal t: follow) {
            System.out.println(t.getLabel());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class Parser.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        Parser instance = new Parser(table, initial);
        instance.parse(lexical);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
