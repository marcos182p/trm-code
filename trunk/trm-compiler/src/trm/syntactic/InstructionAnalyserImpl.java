package trm.syntactic;

import java.util.HashSet;
import java.util.Set;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;
import trm.syntactic.Instruction.InstructionType;

/**
 *  
 */
public class InstructionAnalyserImpl extends CommandAnalyser {
    
    private Set<TokenClass> operandsType;
    private Set<TokenClass> operatorsType;
    
    public InstructionAnalyserImpl(ILexical lexical) {
        super(TokenClass.TK_ID, lexical);
        
        operandsType = new HashSet<TokenClass>();
        operandsType.add(TokenClass.TK_ID);
        operandsType.add(TokenClass.TK_REAL_CTE);
        operandsType.add(TokenClass.TK_INTEGER_CTE);
        operandsType.add(TokenClass.TK_BOOLEAN_CTE);
        
        operatorsType = new HashSet<TokenClass>();
        
        operatorsType.add(TokenClass.TK_ADD);
        operatorsType.add(TokenClass.TK_SUB);
        operatorsType.add(TokenClass.TK_MULT);
        operatorsType.add(TokenClass.TK_DIV);
        operatorsType.add(TokenClass.TK_AND);
        operatorsType.add(TokenClass.TK_OR);
        operatorsType.add(TokenClass.TK_EQUAL);
        operatorsType.add(TokenClass.TK_GREATER_OR_EQUAL);
        operatorsType.add(TokenClass.TK_GREATER_THAN);
        operatorsType.add(TokenClass.TK_LESS_OR_EQUAL);
        operatorsType.add(TokenClass.TK_LESS_THAN);
        operatorsType.add(TokenClass.TK_NOT);
        operatorsType.add(TokenClass.TK_MOD);
        
    }
    private boolean declaration = false;
    private boolean functionCall = false;
    @Override
    protected InstructionType doAnalysis(Token token) {

        switch (nextToken().getTokenClass()) {
            case TK_COMMA:
                doAnalysis(nextToken());
                break;
            //se ele ler esse token os proximos tokens tem que ser de uma declaração
            case TK_COLON:
                declaration();
                return InstructionType.DECLARATION;
            case TK_OPEN_SQUARE_BRACKET:
                declaration = true;
                
                dimensionType();
                
                if (!nextToken().getTokenClass().equals(TokenClass.TK_ATTRIBUTION)) {
                    erro();
                }
            case TK_ATTRIBUTION:
                declaration = true;
                attribution();
                
                
                return InstructionType.ATTRIBUTION;
                
            case TK_OPEN_PARENTHESES:
                functionCall = true;
                functionCall();
                
                return InstructionType.FUNCTION_CALL;
            default:
                erro();

        }
        
        throw new RuntimeException();

    }
    
    private void functionCall() {
        if (!functionCall) {
            erro();
        }
        
        switch (nextToken().getTokenClass()) {
            case TK_ID:
            case TK_INTEGER_CTE:
            case TK_REAL_CTE:
            case TK_BOOLEAN_CTE:
                parameters();
                
                break;
            case TK_CLOSE_PARENTHESES:
                if (!nextToken().getTokenClass().equals(TokenClass.TK_SEMICOLON)) {
                    erro();
                }
                break;
                default:
                    erro();
        }
    }
    
    private void parameters() {
        switch (nextToken().getTokenClass()) {
            case TK_CLOSE_PARENTHESES:
                if (!nextToken().getTokenClass().equals(TokenClass.TK_SEMICOLON)) {
                    erro();
                }
                break;
            case TK_COMMA:
                if (!operandsType.contains(nextToken().getTokenClass())) {
                    erro();
                }
                parameters();
                break;
            case TK_OPEN_SQUARE_BRACKET:
                dimensionId();
                parameters();
                break;
            case TK_SEMICOLON:
                break;
            default:
                erro();
        }
    }
    
    private void dimensionType() {
        switch (nextToken().getTokenClass()) {
            case TK_INTEGER_CTE:
            case TK_ID:
                switch (nextToken().getTokenClass()) {
                    case TK_CLOSE_SQUARE_BRACKET:
                        break;
                    default:
                        erro();
                }
                break;
            default:
                erro();
        }
    }
    private void dimensionId() {
        
        if (!penultimateToken().getTokenClass().equals(TokenClass.TK_ID)) {
            System.out.println(penultimateToken());
            erro();
        }
        dimensionType();
        
    }
    
    private void declaration() {
        
        switch (nextToken().getTokenClass()) {
            case TK_INTEGER:
            case TK_REAL:
                typeDeclaration();
                break;
            default:
                erro();
        }
    }

    private void typeDeclaration() {
        switch (nextToken().getTokenClass()) {
            case TK_OPEN_SQUARE_BRACKET:
                dimensionType();
            case TK_SEMICOLON:
                break;
            default:
                erro();
        }
    }

    private void attribution() {
        if (!declaration) {
            erro();
        }

        expression();
    }
    
    private int parenthesis = 0;

    private void expression() {

        switch (nextToken().getTokenClass()) {
            case TK_ID:
            case TK_INTEGER_CTE:
            case TK_REAL_CTE:
                value();
                break;
            case TK_OPEN_PARENTHESES:
                openParentheses();
                break;
            case TK_CLOSE_PARENTHESES:
                closeParentheses();
                break;
            case TK_NOT:
                expression();
                break;
            case TK_MOD:
            case TK_EQUAL:
            case TK_GREATER_OR_EQUAL:
            case TK_GREATER_THAN:
            case TK_LESS_OR_EQUAL:
            case TK_LESS_THAN:
            case TK_ADD:
            case TK_MULT:
            case TK_DIV:
            case TK_SUB:
            case TK_AND:
            case TK_OR:
                if (!operandsType.contains(penultimateToken().getTokenClass()) &&
                        !penultimateToken().getTokenClass().equals(TokenClass.TK_CLOSE_SQUARE_BRACKET)) {
                    erro();
                }
                expression();
                break;
            case TK_SEMICOLON:
                
                if (!operandsType.contains(penultimateToken().getTokenClass())
                        && !penultimateToken().getTokenClass().equals(TokenClass.TK_CLOSE_PARENTHESES)
                        || parenthesis != 0) {
                    System.out.println(parenthesis);
                    erro();
                }
                
                break;
            case TK_OPEN_SQUARE_BRACKET:
                dimensionId();
                expression();
                break;
            default:
                
                erro();
        }

    }

    private void openParentheses() {
        
        if (!operatorsType.contains(penultimateToken().getTokenClass())) {
            
            if (getTokens().size() > 2) {
                erro();

            }
            
        }
        parenthesis++;
        expression();
    }
    
    private void value() {
        expression();
    }
    
    private void closeParentheses() {
        if (!operandsType.contains(penultimateToken().getTokenClass()) &&
                !penultimateToken().getTokenClass().equals(TokenClass.TK_CLOSE_PARENTHESES)&&
                !penultimateToken().getTokenClass().equals(TokenClass.TK_CLOSE_SQUARE_BRACKET)) {

            erro();

        }
        parenthesis--;
        expression();
    }

    public static void main(String[] args) {
        ILexical lexical = new LexicalAnalyzer("x_test");
        InstructionAnalyserImpl parserId = new InstructionAnalyserImpl(lexical);
        parserId.analyze(lexical.nextToken());

        for (Token token : parserId.getTokens()) {
            System.out.println(token.getTokenClass());

        }
    }
}
