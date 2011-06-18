package trm.syntactic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import trm.lexical.Token;

/**
 *
 */
public class Instruction {
    
    public enum InstructionType {

        DECLARATION, ATTRIBUTION, FOR, WHILE, FUNCTION, FUNCTION_CALL, IF, ELSE
    }
    
    private List<Token> tokens;
    
    private InstructionType type;
    
    private int start;
    private int end;

    public Instruction(InstructionType type, List<Token> tokens) {
        this.tokens = tokens;
        this.type = type;
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }
    
    public InstructionType getType() {
        return type;
    }
    
    public Token lastToken() {
        return tokens.get(tokens.size() - 1);
    }
    
    public void addToken(Token token) {
        tokens.add(token);
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }
    
    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
