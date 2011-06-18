package trm.syntactic;

import java.util.ArrayList;
import java.util.List;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/**
 *
 * @author Rafael
 */
public class InstructionId extends InstructionAnalyser {

    private InstructionType instructionType;

    public static void main(String[] args) {
        ILexical lexical = new LexicalAnalyzer("x_test");
        InstructionId i = new InstructionId(lexical);
        i.analyze(lexical.nextToken());

        for (Token token: i.tokens) {
            System.out.println(token.getTokenClass());
        }
    }

    public InstructionId(ILexical lexical) {


        super(TokenClass.TK_ID, lexical);
        tokens = new ArrayList<Token>();
    }

    public List<Token> tokens;

    private Token nextToken() {
        Token nextToken = lexical.nextToken();
        tokens.add(nextToken);
        return nextToken;
    }

    @Override
    protected void doAnalysis(Token token) {

        instructionType = null;
        switch (nextToken().getTokenClass()) {
            case TK_COMMA:
                doAnalysis(nextToken());
                break;
            case TK_COLON:
                colon();
                break;
            
            default:
                erro();

        }

    }

    private enum InstructionType {
        DECLARATION
    }

    private void colon() {
        switch (nextToken().getTokenClass()) {
            case TK_INTEGER:
            case TK_REAL:
                switch(nextToken().getTokenClass()) {
                    case TK_OPEN_SQUARE_BRACKET:
                        openSquareBracket();
                    case TK_SEMICOLON:
                        instructionType = InstructionType.DECLARATION;
                        break;
                    default:
                        erro();

                }
                break;
            default:
                erro();

        }
    }

    private void openSquareBracket() {
        switch (nextToken().getTokenClass()) {
            case TK_INTEGER_CTE:
            case TK_ID:
                switch (nextToken().getTokenClass()) {
                    case TK_CLOSE_SQUARE_BRACKET:
                        if (nextToken().getTokenClass() == TokenClass.TK_SEMICOLON) {
                            instructionType = InstructionType.DECLARATION;
                        } else {
                            erro();
                        }
                        break;
                    default:
                        erro();
                }

        }
    }

    private void erro() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
