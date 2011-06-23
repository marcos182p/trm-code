package trm.syntactic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import trm.lexical.ILexical;
import trm.lexical.Token;
import trm.lexical.TokenClass;

/** Classe GLCAnalyser - Responsável analisar uma GLC
 * @author TRM
 * @version 0.99
 */
public class GLCAnalyser {

    private GLC glc;
    private PreditiveTable table;
    private Stack<Element> variables;
    private List<Token> readTokens;

    /** Construtor GLCAnalyser da Classe - Padrão
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro
     * @param  glc GLC - GLC da linguagem
     */
    public GLCAnalyser(GLC glc) {
        this.table = TableGenerator.createPreditiveTable(glc);
        this.glc = glc;
    }

    /** 
     * Método que inicializa a análise
     */
    private void initParser() {
        variables = new Stack<Element>();
        variables.push(new Terminal(TokenClass.TK_EOF));
        variables.push(glc.getInitialElement());
        readTokens = new ArrayList<Token>();
    }

    /** Método que análisa a gramatica.
     * @param lexical ILexical - Analisador léxico
     */
    public void analysis(ILexical lexical) {

        //prepara para a inicialização da análise
        initParser();

        //seta as variaveis como null
        Token token = null;
        Token lastToken = null;

        //Enquanto existir tokens para serem lidos
        while ((token = lexical.nextToken()) != null) {

            //Adiciona o token na lista de tokens lidos
            readTokens.add(token);

            //Se o conjunto de variaveis for vazio levanta um erro
            if (variables.isEmpty()) {
                erro(token);
            }

            //Instância um novo terminal
            Terminal terminal = new Terminal(token.getTokenClass());

            Element top = null;

            //Enquanto o topo não for terminal
            while (!((top = variables.pop()) instanceof Terminal)) {

                //Pega o topo
                Variable var = (Variable) top;

                //Pega a derivação da tabela
                Derivation derivation = table.getDerivation(var, terminal);

                //Caso não exista derivação
                if (derivation == null) {
                    erro(token);
                }

                //Pega os elementos alvo da derivação
                List<Element> elements = derivation.getTargets();

                //Adiciona na pilha
                for (int i = elements.size() - 1; i >= 0; i--) {
                    variables.push(elements.get(i));
                }
            }
            //Se o topo não for terminal, erro!
            if (!top.equals(terminal)) {
                erro(token);
            }

            //ultimo token
            lastToken = token;
        }
        //Se o conjunto de variaveis não for vazio, erro!
        if (!variables.isEmpty()) {
            erro(lastToken);
        }
    }

    /** Método que retorna a lista de Tokens Lidos
     * @return List<Token> - Lista de tokens lidos
     */
    public List<Token> getReadTokens() {
        return readTokens;
    }

    /** Método de erro
     * @param token Token - Token que causou o erro
     * @throws RuntimeException - Contendo a mensagem de erro
     */
    private void erro(Token token) {
        throw new RuntimeException("erro de sintaxe em: " + token.getLine()
                + ", " + token.getcolumn());
    }
}
