package trm.syntactic;

import java.util.ArrayList;
import java.util.List;
import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;
import trm.syntactic.Instruction.InstructionType;

/**
 *
 */
public abstract class CommandAnalyser {
    
    private TokenClass initial;
    private ILexical lexical;

    private List<Token> tokens;
    
    public CommandAnalyser(TokenClass initial, ILexical lexical) {
        this.initial = initial;
        this.lexical = lexical;
    }
    
    protected Token nextToken() {
        Token nextToken = lexical.nextToken();
        tokens.add(nextToken);
        return nextToken;
    }
    
    protected Token penultimateToken() {
        return tokens.get(tokens.size() - 2);
    }

    public Instruction analyze(Token token) {
        if (!initial.equals(token.getTokenClass())) {
            throw new RuntimeException("não responsavel por analisar esse token");
        }
        tokens = new ArrayList<Token>();
        tokens.add(token);
        InstructionType instructionType = doAnalysis(token);
        return new Instruction(instructionType, tokens);
    }

    protected  abstract InstructionType doAnalysis(Token token) ;
    
    protected void erro() {
        Token lastToken = getTokens().get(getTokens().size() - 1);

        throw new RuntimeException("erro ao analisar o token " + lastToken);
    }
    
    public List<Token> getTokens() {
        return tokens;
    }
    
}
