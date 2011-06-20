package trm.syntactic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;
import trm.syntactic.Instruction.InstructionType;

/**
 *
 */
public class SyntacticAnalyserImpl implements SyntacticAnalyser {
    
    private Set<InstructionType> blocksType;
    
    private List<Instruction> instructions;
    /**
     * usado para registrar os blocos de código, sabe onde eles começão e 
     * terminam
     */
    private Stack<Instruction> blocks;
    
    public SyntacticAnalyserImpl() {
        instructions = new ArrayList<Instruction>();
        
        blocks = new Stack<Instruction>();
        blocksType = new HashSet<InstructionType>() {{
            add(InstructionType.FOR);
            add(InstructionType.FUNCTION);
            add(InstructionType.WHILE);
            add(InstructionType.IF);
            add(InstructionType.ELSE);
        }};
    }
    
    @Override
    public void parse(ILexical lexical) {
        //primeira chamada de tem que ser a declaração de uma função
        Token token = null;
        while ((token = lexical.nextToken()) != null) {
            switch (token.getTokenClass()) {
                
                case TK_ID:
                    Instruction functionInstruction = 
                            new FuncitionAnalayser(lexical).analyze(token);
                    
                    instructions.add(functionInstruction);
                    pushBlock();
                    
                    instruction(lexical);
                    break;
                default:
                    throw new RuntimeException("");
            }
        }
        
    }
    
    private void pushBlock() {
        
        int start = instructions.size() - 1;
        
        Instruction instruction = instructions.get(start);
        if (!blocksType.contains(instruction.getType())) {
            throw new RuntimeException("essa instrução não é de bloco");
        }
        
        instruction.setStart(start);
        
        blocks.push(instruction);
    }
    
    /**
     * metodo que marca o fim de um bloco de código
     */
    private void popBlock() {
        
        //ultima instrução do bloco
        int end = instructions.size() - 1;
        
        //instrução do inicio do bloco
        Instruction startBlock = blocks.pop();
        
        startBlock.setEnd(end);
        
    }
    
    private void saveInstruction(Instruction instruction) {
        instructions.add(instruction);
        if (blocksType.contains(instruction.getType())) {
            pushBlock();
        }

    }
    
    private void instruction(ILexical lexical) {
        Token token = lexical.nextToken();
        
        here : while ((token = lexical.nextToken()) != null) {
            switch (token.getTokenClass()) {
                case TK_ID:
                    saveInstruction(
                            new CommandAnalyserImpl(lexical).analyze(token));
                    break;
                case TK_FOR:
                    //analisar se o 'for' é valido, marcar seu inicio
                    saveInstruction(null);
                    break;
                case TK_IF:
                    //analisar se o 'if' é valido, marcar seu inicio
                    saveInstruction(null);
                case TK_ELSE:
                    //analisar se o 'else' é valido, marcar seu inicio
                    saveInstruction(null);
                    break;
                case TK_CLOSE_CURLY_BRACKET:
                    //verificar a quem ele pertecence
                    popBlock();
                    if (blocks.isEmpty()) {
                        break here;
                    }
                    break;
                default:
                    throw new RuntimeException("");
            }
        }
        //fazer analise do codigo dentro da função
        
    }
    public static void main(String[] args) {
//        here :while (true) {
//            int i = 1;
//            switch (i)  {
//                case 1:
//                    break here;
//                        
//            }
//        }
        
        ILexical lexical = new LexicalAnalyzer("x_test");
        FuncitionAnalayser parserId = new FuncitionAnalayser(lexical);
        parserId.analyze(lexical.nextToken());

        for (Token token : parserId.getTokens()) {
            System.out.println(token.getTokenClass());

        }
    }

    /**
     * analisa o protipo de uma função
     */
    private static class FuncitionAnalayser extends CommandAnalyser {

        public FuncitionAnalayser(ILexical lexical) {
            super(TokenClass.TK_ID, lexical);
        }

        @Override
        protected InstructionType doAnalysis(Token token) {
            //TODO implementar! esse metodo ira analisar se o prototipo da 
            //função é valida
            return InstructionType.FUNCTION;
        }

    }
    
}
