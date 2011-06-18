package trm.syntactic;

import java.util.HashSet;
import java.util.Set;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/**
 *  
 */
public class HandlerId extends InstructionAnalyser {
    
    private Set<TokenClass> operandsType;
    private Set<TokenClass> operatorsType;
    
    public HandlerId(ILexical lexical) {
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
    @Override
    protected void doAnalysis(Token token) {

        switch (nextToken().getTokenClass()) {
            case TK_COMMA:
                doAnalysis(nextToken());
                break;
            //se ele ler esse token os proximos tokens tem que ser de uma declaração
            case TK_COLON:
                colon();
                break;
            case TK_OPEN_SQUARE_BRACKET:
                declaration = true;
                dimensionType();
                if (!nextToken().getTokenClass().equals(TokenClass.TK_ATTRIBUTION)) {
                    erro();
                }
            case TK_ATTRIBUTION:
                declaration = true;
                attribution();
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
            erro();
        }
        dimensionType();
        
    }
    
    private void colon() {
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
                type = InstructionType.DECLARATION;
                break;
            default:
                erro();

        }
        type = InstructionType.DECLARATION;
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
        HandlerId parserId = new HandlerId(lexical);
        parserId.analyze(lexical.nextToken());

        for (Token token : parserId.getTokens()) {
            System.out.println(token.getTokenClass());

        }
    }
}
