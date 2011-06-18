package trm.syntactic;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import trm.lexical.TokenClass;

/**
 *
 */
public class BlockInstruction {
    
    public enum BlockType {
        FOR_BLOCK, WHILE_BLOCK, FUNCTION_BLOCK
    }

    private List<Instruction> instructions;

    public BlockInstruction() {
        instructions = new ArrayList<Instruction>();
    }
    
}
