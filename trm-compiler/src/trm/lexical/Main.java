package trm.lexical;

/** Classe Main - Classe responsável pela inicialização do programa Teste
 * @author TRM
 * @version 0.99
 */
public class Main {

    /** Programa Teste que executa o analisador léxico, listando
     * para cada token sua posição (linha e coluna) no programa exemplo,
     * sua classe e seu valor se houver.
     * @param  args String[] - Argumentos que forem necessários
     */
    public static void main(String[] args) {

        //Variaveis temporárias para indicar o token corrente e seu valor
        Token newToken;
        String valueToken;
        String pathFile = "src/shellsort"; // Caminho do arquivo a ser analisado
        String nameFile = pathFile.split("/")[1];

        //Instanciação do analisador Léxico
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();

        //Setando o local do arquivo
        lexicalAnalyzer.setPathFile(pathFile);

        //Impressão na tela
        System.out.println("Tokens Gerados para o arquivo " + nameFile + ": \n");

        /* Chamando o metodo nextToken até não existir mais tokens
         * OBS: Esse método será chamado pelo analisador sintático
         */
        while ((newToken = lexicalAnalyzer.nextToken()) != null) {

            //Setando o valor do token, se existir
            if (newToken.getTokenClass().isUnivoque()) {
                valueToken = "";
            } else {
                valueToken = " - Valor: " + newToken.getValue();
            }

            //Impressão dos tokens na tela
            System.out.println("Classe: " + newToken.getTokenClass() + " - "
                    + "Posição: Linha=" + newToken.getLine() + " Coluna="
                    + newToken.getcolumn() + valueToken);
        }
    }
}
