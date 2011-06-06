package trm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collection;

/** Classe LexicalAnalyzer - Analisador Lexico da linguagem
 * @author TRM
 * @version 0.99
 */
public class LexicalAnalyzer implements ILexical {

    private String pathFile;

    /** Construtor LexicalAnalyzer da Classe -
     * Inicializa os atributos da classe com os valores recebidos
     * como parâmetro.
     * @param  pathFile String - Caminho do arquivo a ser analisado
     */
    public LexicalAnalyzer(String pathFile) {

        this.pathFile = pathFile;
    }

    /** Construtor LexicalAnalyzer da Classe - Padrão
     */
    public LexicalAnalyzer() {
    }

    /** Metodo que seta o pathFile
     * @param  pathFile String - Novo valor do pathFile
     * @return void 
     */
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    /** Método que analisa o arquivo de entrada (programa)
     * @return Collection<Token> - Lista de tokens gerados
     * depois da análise lexica
     */
    public Collection<Token> parse() {
        Automaton automaton = AutomatonFactory.createAutomaton();

        String fileText = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.pathFile)));

            while (reader.ready()) {
                fileText += reader.readLine() + System.getProperty("line.separator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Recognizer recognizer = new Recognizer(automaton, fileText.toCharArray());
        recognizer.run();

        return recognizer.getTokens();
    }
}
