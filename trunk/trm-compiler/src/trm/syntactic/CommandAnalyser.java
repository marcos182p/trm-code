package trm.syntactic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;
import trm.syntactic.Instruction.InstructionType;

/**
 *
 */
public abstract class CommandAnalyser {
    
    private TokenClass initial;
    protected ILexical lexical;
    
    protected List<Token> tokens;
    
    public CommandAnalyser(TokenClass initial, ILexical lexical) {
        this.initial = initial;
        this.lexical = lexical;
    }
    
    public Instruction analyze(Token token) {
        if (!initial.equals(token.getTokenClass())) {
            throw new RuntimeException("não responsavel por analisar esse token");
        }
        tokens = new ArrayList<Token>();
        InstructionType instructionType = doAnalysis(token);
        return new Instruction(instructionType, tokens);
    }

    protected  abstract InstructionType doAnalysis(Token token) ;
    
    /**
     * Analisa a instrução definida pelo GLC tendo o ';' como ultimo token
     */
    protected void analysiInstruction(GLC glc) {

        GLCAnalyser analyser = new GLCAnalyser(glc);

        try {
            analyser.analysis(lexical);

            throw new RuntimeException();

        } catch (Exception e) {

            final List<Token> tks = analyser.getReadTokens();
            int lastToken = tks.size() - 1;
            Token semiColon = tks.get(lastToken);
            
            tokens.addAll(tks);

            tks.remove(lastToken);

            if (!semiColon.getTokenClass().equals(TokenClass.TK_SEMICOLON)) {
                erro();
            }

            analyser.analysis(new ILexical() {

                Queue<Token> tokensQueue = new ArrayDeque<Token>(tks);

                public Token nextToken() {
                    return tokensQueue.poll();
                }
            });
        }
    }
    
    protected void erro() {
        Token lastToken = getTokens().get(getTokens().size() - 1);

        throw new RuntimeException("erro ao analisar o token " + lastToken);
    }
    
    public List<Token> getTokens() {
        return tokens;
    }
    
}
