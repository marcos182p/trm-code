package trm.syntactic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
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

        analysi(glc, TokenClass.TK_SEMICOLON, true, false);
    }
    
    
    protected void analysi(GLC glc, TokenClass last, boolean remove, boolean relocate) {
        
        GLCAnalyser analyser = new GLCAnalyser(glc);

        try {
            analyser.analysis(lexical);

            throw new RuntimeException();

        } catch (Exception e) {
            
            final List<Token> tks = analyser.getReadTokens();
            
            Token lastToken = tks.get(tks.size() - 1);
            
            //verifica se o ultimo token precisa ser removido
            if (remove) {
                tks.remove(tks.size() - 1);
            }
            
            if (relocate) {
                ((LexicalAnalyzer) lexical).putToken(lastToken);
            }
            
            tokens.addAll(tks);
            
            tks.add(new Token(null, TokenClass.TK_EOF, lastToken.getLine(),
                    lastToken.getcolumn()));
            
            if (!lastToken.getTokenClass().equals(last) && last != null) {
                erro();
            }
            try {

                analyser.analysis(new ILexical() {

                    Queue<Token> tokensQueue = new ArrayDeque<Token>(tks);

                    public Token nextToken() {
                        return tokensQueue.poll();
                    }
                });
            } catch (Exception ex) {
                erro();
            }
            
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
