package trm.syntactic;

import trm.lexical.ILexical;
import trm.lexical.LexicalAnalyzer;
import trm.lexical.Token;
import trm.lexical.TokenClass;


/** Classe CommandAnalyserImpl - Implementação da classe CommandAnalyser -
 * Responsável por analisar os comandos da linguagem X
 * @author TRM
 * @version 0.99
 */
public class CommandAnalyserImpl extends CommandAnalyser {

    /** Construtor CommandAnalyserImpl da Classe - Padrão
     * Chama o construtor da superclasse e passa como parâmetro os valores
     * recebidos como parâmetro e a classe do Token
     * @param  lexical ILexical - Instância de um analisador léxico
     */
    public CommandAnalyserImpl(ILexical lexical) {
        super(TokenClass.TK_ID, lexical);
    }

    @Override
    /** Método que analisa um token e retorna o tipo de instrução no qual ele
     * pertence
     * @param  token Token - Token a ser analisado
     * @return InstructionType - Tipo de instrução do token
     */
    protected InstructionType doAnalysis(Token token) {

        //Pega o novo token
        Token temp = lexical.nextToken();

        //Olha para frente e readiciona na lista...
        ((LexicalAnalyzer) lexical).putToken(token);
        ((LexicalAnalyzer) lexical).putToken(temp);

        //Escolhas para o tipo de instrução
        switch (temp.getTokenClass()) {

            /* Se ele ler algum desses tokens a instrução possivelmente
             * será uma declaração
             */
            case TK_COMMA:
            case TK_COLON:
                GLC declaration = GLCFactory.createGLCDeclaration();
                analysiInstruction(declaration);

                return InstructionType.DECLARATION;

            /* Se ele ler algum desses tokens a instrução possivelmente
             * será uma atribuição
             */
            case TK_OPEN_SQUARE_BRACKET:
            case TK_ATTRIBUTION:
                GLC atribution = GLCFactory.createGLCAtribuition();
                analysiInstruction(atribution);
                return InstructionType.ATTRIBUTION;

            /* Se ele ler esse token a instrução possivelmente
             * será uma chamada de função
             */
            case TK_OPEN_PARENTHESES:

                lexical.nextToken();
                GLC functionCall = GLCFactory.createGLCFunctionCall();
                analysiInstruction(functionCall);
                return InstructionType.FUNCTION_CALL;

            /* Caso não reconheça nenhum tipo de instrução, será chamada a
             * função erro
             */
            default:
                erro();
        }

        throw new RuntimeException("Erro desconhecido!");
    }
}
