/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.syntactic;

import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/**
 *
 * @author Rafael
 */
public abstract class InstructionAnalyser {

    private TokenClass initial;
    protected ILexical lexical;

    public InstructionAnalyser(TokenClass initial, ILexical lexical) {
        this.initial = initial;
        this.lexical = lexical;
    }

    void analyze(Token token) {
        if (initial != token.getTokenClass()) {
            throw new RuntimeException("não responsavel por analisar esse token");
        }
        doAnalysis(token);
    }

    protected  abstract void doAnalysis(Token token) ;
}
