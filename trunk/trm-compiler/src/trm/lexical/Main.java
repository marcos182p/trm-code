package trm.lexical;

/** Classe Main - Classe responsável pela inicialização do programa
 * @author TRM
 * @version 0.99
 */
public class Main {

    /** Método que inicializa a análise
     * @param  args String[] - Argumentos que forem necessários
     */
    public static void main(String[] args) {

        LexicalAnalyzer analisadorLexico = new LexicalAnalyzer();
        analisadorLexico.setPathFile("src/serie_fibonnaci");

        while (analisadorLexico.nextToken() != null) {
        }

    }
}
