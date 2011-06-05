package trm.lexical;

import java.util.List;

/** Classe Main - Classe responsável pela inicialização do programa
 * @author TRM
 * @version 0.99
 */
public class Main {

    /** Método que inicializa a análise
     * @param  args String[] - Argumentos que forem necessários
     * @return void
     */
    public static void main(String[] args) {

        List<Token> lista;
        LexicalAnalyzer analisadorLexico = new LexicalAnalyzer();

        /*
        //Análise do arquivo alo_mundo
        analisadorLexico.setPathFile("src/alo_mundo");
        lista = (List<Token>) analisadorLexico.parse();
        System.out.println("\nQuantidade de Tokens Gerados pelo arquivo alo_mundo: " + lista.size() + "\n");
        */

        /*
        //Análise do arquivo serie_fibonnaci.txt
        analisadorLexico.setPathFile("src/serie_fibonnaci");
        lista = (List<Token>) analisadorLexico.parse();
        System.out.println("\nQuantidade de Tokens Gerados pela serie de fibonnaci: " + lista.size() + "\n");
         */

        
        //Análise do arquivo serie_fibonnaci.txt
        analisadorLexico.setPathFile("src/shellsort");
        lista = (List<Token>) analisadorLexico.parse();
        System.out.println("\nQuantidade de Tokens Gerados pelo arquivo shellsort: " + lista.size() + "\n");
         



    }
}
