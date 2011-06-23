package trm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.Queue;

/** Classe LexicalAnalyzer - Analisador Lexico da linguagem
 * @author TRM
 * @version 0.99
 */
public class LexicalAnalyzer implements ILexical {

    private String pathFile;
    private Recognizer recognizer;
    private Queue<Token> tokensQueue = new ArrayDeque<Token>();

    /** Construtor LexicalAnalyzer da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro. E inicializa o analisador lexico
     * @param  pathFile String - Caminho do arquivo a ser analisado
     */
    public LexicalAnalyzer(String pathFile) {
        setPathFile(pathFile);
    }

    /** Construtor LexicalAnalyzer da Classe - Padrão
     */
    public LexicalAnalyzer() {
    }

    /** Metodo que seta o pathFile
     * @param  pathFile String - Novo valor do pathFile
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
        initLexicalAnalyzer();
    }

    /** Metodo que inicializa o analisador Lexico
     * @return void
     */
    private void initLexicalAnalyzer() {
        Automaton automaton = AutomatonFactory.createAutomaton();

        String fileText = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    new File(this.pathFile)));

            while (reader.ready()) {
                fileText += reader.readLine()
                        + System.getProperty("line.separator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileText += " eof";
        recognizer = new Recognizer(automaton, fileText.toCharArray());
    }

    /** Metodo que coloca um token na fila
     * @param token Token - Token a ser inserido na fila
     */
    public void putToken(Token token) {
        tokensQueue.offer(token);
    }

    /** Método que analisa o arquivo de entrada (programa)
     * @return Token - Proximo token Gerado
     * depois da análise lexica
     */
    public Token nextToken() {
        Token token = tokensQueue.poll();
        if (token == null) {
            token = recognizer.run();
        }

        return token;
    }
}
