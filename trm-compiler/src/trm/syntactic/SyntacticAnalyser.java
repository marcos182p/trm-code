package trm.syntactic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/** Classe SyntacticAnalyser - Analisador Sintático da linguagem
 * @author TRM
 * @version 0.99
 */
public class SyntacticAnalyser implements ISyntacticAnalyser {

    private Set<InstructionType> blocksType;
    private List<Instruction> instructions;
    /**
     * Usado para registrar os blocos de código, com a finalidade de sabee
     * o inicio e o final de cada um deles
     */
    private Stack<Instruction> blocks;

    /** Construtor SyntacticAnalyser da Classe -
     * Inicializa os atributos da classe com os valores necessários
     */
    public SyntacticAnalyser() {
        instructions = new ArrayList<Instruction>();

        /*
         * Inicializa o blocks e adiciona os tipos de instruções de bloco
         * existentes no Set
         */
        blocks = new Stack<Instruction>();
        blocksType = new HashSet<InstructionType>() {

            {
                add(InstructionType.FOR);
                add(InstructionType.FUNCTION);
                add(InstructionType.WHILE);
                add(InstructionType.IF);
                add(InstructionType.ELSE);
            }
        };
    }

    @Override
    /** Método que analisa determinada entidade.
     * @param lexical ILexical - Analizador léxico
     */
    public void parse(ILexical lexical) {

        //Primeira chamada tem que ser a declaração de uma função
        Token token = null;

        //Percorrer a lista de tokens até que não exista mais
        while ((token = lexical.nextToken()) != null) {
            //Escolha do tokens
            switch (token.getTokenClass()) {

                //Caso o token id
                case TK_ID:
                    Instruction functionInstruction =
                            new FunctionAnalyser(lexical).analyze(token);

                    saveInstruction(functionInstruction);

                    instruction(lexical);
                    break;

                //Caso o token fim de arquivo
                case TK_EOF:
                    break;

                //Nenhum deles - Erro
                default:
                    erro(token);
            }
        }

    }

    /** Método que retorna a lista de instruções
     * @return List<Instruction> - Lista de instruções
     */
    public List<Instruction> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }

    /**
     * Método que adiciona o bloco na pilha
     */
    private void pushBlock() {

        int start = instructions.size() - 1;

        Instruction instruction = instructions.get(start);
        if (!blocksType.contains(instruction.getType())) {
            throw new RuntimeException("Essa instrução não é de bloco!");
        }

        blocks.push(instruction);
    }

    /**
     * Metódo que marca o fim de um bloco de código
     */
    private void popBlock() {

        //Ultima instrução do bloco
        int end = instructions.size();

        //Instrução do inicio do bloco
        Instruction startBlock = blocks.pop();

        if (startBlock == null) {
            throw new RuntimeException("Erro no bloco de código!");
        }

        instructions.get(end - 1).setStart(startBlock.getStart());
        startBlock.setEnd(end);
    }

    /** Método que salva uma instrução
     * @param instruction Instruction - Instrução a ser salva
     */
    private void saveInstruction(Instruction instruction) {
        instructions.add(instruction);
        instruction.setStart(instructions.size());
        instruction.setEnd(instructions.size());
        if (blocksType.contains(instruction.getType())) {
            pushBlock();
        }

    }

    /** Método que pega a instrução e salva
     * @param lexical ILexical - Analizador léxico
     */
    private void instruction(ILexical lexical) {
        Token token = null;

        here:
        while ((token = lexical.nextToken()) != null) {

            //Escolha da nova instrução
            switch (token.getTokenClass()) {

                //Caso return
                case TK_RETURN:
                    saveInstruction(new ReturnAnalayser(lexical).analyze(token));
                    break;

                //Caso id
                case TK_ID:
                    saveInstruction(
                            new CommandAnalyserImpl(lexical).analyze(token));
                    break;

                //Caso for
                case TK_FOR:
                    saveInstruction(new ForAnalayser(lexical).analyze(token));
                    break;

                //Caso while
                case TK_WHILE:
                    saveInstruction(new WhileAnalayser(lexical).analyze(token));
                    break;

                //Caso if
                case TK_IF:
                    saveInstruction(new IfAnalayser(lexical).analyze(token));
                    break;

                //Caso else
                case TK_ELSE:
                    saveInstruction(new ElseAnalayser(lexical).analyze(token));
                    break;

                //Caso fecha chaves
                case TK_CLOSE_CURLY_BRACKET:
                    final Token temp = token;
                    saveInstruction(new Instruction(InstructionType.END_BLOCK,
                            new ArrayList<Token>() {

                                {
                                    add(temp);
                                }
                            }));
                    popBlock();

                    if (blocks.isEmpty()) {

                        break here;
                    }
                    break;

                //Caso nenhum - Erro!
                default:
                    erro(token);
            }
        }
    }

    /** Método de erro
     * @param token Token - Token que causou o erro
     * @throws RuntimeException - Contendo a mensagem de erro
     */
    private void erro(Token token) {
        throw new RuntimeException("erro no token " + token);

    }

    /** Classe FunctionAnalyser - Implementação da classe CommandAnalyser -
     * Responsável por analisar as funções da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class FunctionAnalyser extends CommandAnalyser {

        /** Construtor FunctionAnalyser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public FunctionAnalyser(ILexical lexical) {
            super(TokenClass.TK_ID, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);

            GLC glcFuntionDeclaration = GLCFactory.createGLCFuntionDeclaration();
            analysi(glcFuntionDeclaration, null, true, true);

            return InstructionType.FUNCTION;
        }
    }

    /** Classe ForAnalayser - Implementação da classe CommandAnalyser -
     * Responsável por analisar o comando for da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class ForAnalayser extends CommandAnalyser {

        /** Construtor ForAnalayser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public ForAnalayser(ILexical lexical) {
            super(TokenClass.TK_FOR, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);


            GLC glcForDeclaration = GLCFactory.createGLCFor();
            analysi(glcForDeclaration, null, true, true);

            return InstructionType.FOR;
        }
    }

    /** Classe WhileAnalayser - Implementação da classe CommandAnalyser -
     * Responsável por analisar o comando while da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class WhileAnalayser extends CommandAnalyser {

        /** Construtor WhileAnalayser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public WhileAnalayser(ILexical lexical) {
            super(TokenClass.TK_WHILE, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);


            GLC glcWhileDeclaration = GLCFactory.createGLCWhile();
            analysi(glcWhileDeclaration, null, true, true);

            return InstructionType.WHILE;
        }
    }

    /** Classe IfAnalayser - Implementação da classe CommandAnalyser -
     * Responsável por analisar o comando if da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class IfAnalayser extends CommandAnalyser {

        /** Construtor IfAnalayser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public IfAnalayser(ILexical lexical) {
            super(TokenClass.TK_IF, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);


            GLC glcIfDeclaration = GLCFactory.createGLCIf();
            analysi(glcIfDeclaration, null, true, true);

            return InstructionType.IF;
        }
    }

    /** Classe ElseAnalayser - Implementação da classe CommandAnalyser -
     * Responsável por analisar o comando else da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class ElseAnalayser extends CommandAnalyser {

        /** Construtor ElseAnalayser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public ElseAnalayser(ILexical lexical) {
            super(TokenClass.TK_ELSE, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);


            GLC glcElseDeclaration = GLCFactory.createGLCElse();
            analysi(glcElseDeclaration, null, true, true);

            return InstructionType.ELSE;
        }
    }

    /** Classe ReturnAnalayser - Implementação da classe CommandAnalyser -
     * Responsável por analisar o comando return da linguagem X
     * @author TRM
     * @version 0.99
     */
    private static class ReturnAnalayser extends CommandAnalyser {

        /** Construtor ReturnAnalayser da Classe - Padrão
         * Chama o construtor da superclasse e passa como parâmetro os valores
         * recebidos como parâmetro e a classe do Token
         * @param  lexical ILexical - Instância de um analisador léxico
         */
        public ReturnAnalayser(ILexical lexical) {
            super(TokenClass.TK_RETURN, lexical);
        }

        @Override
        /** Método que analisa um token e retorna o tipo de instrução no qual ele
         * pertence
         * @param  token Token - Token a ser analisado
         * @return InstructionType - Tipo de instrução do token
         */
        protected InstructionType doAnalysis(Token token) {

            ((LexicalAnalyzer) lexical).putToken(token);


            GLC glcReturnDeclaration = GLCFactory.createGLCReturn();
            analysiInstruction(glcReturnDeclaration);

            return InstructionType.RETURN;
        }
    }
}
