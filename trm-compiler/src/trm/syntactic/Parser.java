/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.syntactic;

import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.Token;

/**
 *
 * @author marcos182p
 */
public class Parser implements SyntacticAnalyser{
    
    private ILexical analyzer;
    private PreditiveTable table;
    private Stack<Token> tokensReaded;
    private Stack<Variable> variableReaded;

    public Parser(ILexical lexical, PreditiveTable table) {
        this.analyzer = lexical;
        this.table = table;
        
    }

    public void parse() {
        
    }
}
