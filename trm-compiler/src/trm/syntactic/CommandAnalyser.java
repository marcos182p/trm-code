package trm.syntactic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/** Classe Abstrata CommandAnalyser - Responsável por analisar os comandos
 * da linguagem X
 * @author TRM
 * @version 0.99
 */
public abstract class CommandAnalyser {

    private TokenClass initial;
    protected ILexical lexical;
    protected List<Token> tokens;

    /** Construtor CommandAnalyser da Classe - Padrão
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  initial TokenClass - Classe do token inicial
     * @param  lexical ILexical - Instância de um analisador léxico
     */
    public CommandAnalyser(TokenClass initial, ILexical lexical) {
        this.initial = initial;
        this.lexical = lexical;
    }

    /** Método que analisa o token inicial e retorna uma instrução
     * @param  token Token - Token a ser analisado
     * @return Instruction - Instrução criada depois da analise do token
     * @throws RuntimeException - Caso o token não possa ser avaliado por
     * este metódo
     */
    public Instruction analyze(Token token) {

        //Caso em que o token não pode ser avaliado
        if (!initial.equals(token.getTokenClass())) {
            throw new RuntimeException("Metódo não responsável por analisar "
                    + "esse token!");
        }
        tokens = new ArrayList<Token>();

        //Verifica a qual tipo de instrução o token pertence
        InstructionType instructionType = doAnalysis(token);

        /*Retorna uma nova instrução, com o tipo definido e os tokens pertencentes
         *a ela
         */
        return new Instruction(instructionType, tokens);
    }

    /** Método que analisa um token e retorna o tipo de instrução no qual ele
     * pertence
     * @param  token Token - Token a ser analisado
     * @return InstructionType - Tipo de instrução do token
     */
    protected abstract InstructionType doAnalysis(Token token);

    /** Método que analisa uma instrução definida pelo GLC tendo o ';'
     * como ultimo token
     * @param  glc GLC - Gramatica de avaliação
     */
    protected void analysiInstruction(GLC glc) {
        analysi(glc, TokenClass.TK_SEMICOLON, true, false);
    }

    /** Método que analisa uma instrução definida
     * @param  glc GLC - Gramatica de avaliação
     * @param  last TokenClass - Classe do token
     * @param  remove boolean - Se o token deve ou não ser removido
     * @param  relocate boolean - Se o token deve ou não ser realocado
     */
    protected void analysi(GLC glc, TokenClass last, boolean remove, boolean relocate) {

        //Cria um analisador da grámatica, com parâmetro a gramatica GLC
        GLCAnalyser analyser = new GLCAnalyser(glc);

        try {
            analyser.analysis(lexical);

            throw new RuntimeException();

        } catch (Exception e) {

            final List<Token> tks = analyser.getReadTokens();

            Token lastToken = tks.get(tks.size() - 1);

            // Verifica se o ultimo token precisa ser removido
            if (remove) {
                tks.remove(tks.size() - 1);
            }

            // Verifica se o ultimo token precisa ser realocado
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

    /** Método chamado caso exista algum erro na análise sintática
     * @throws RuntimeException - Mensagem informando o erro de análise
     * sintática
     */
    protected void erro() {

        //Pega o ultimo token da lista
        Token lastToken = getTokens().get(getTokens().size() - 1);

        //Levanta a exeção informando a mensagem de erro
        throw new RuntimeException("Erro ao analisar o token " + lastToken);
    }

    /** Método que retorna a lista de Tokens
     * @return List<Token> - Lista de tokens
     */
    public List<Token> getTokens() {
        return tokens;
    }
}
