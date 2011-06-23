package trm.syntactic;

import trm.lexical.LexicalAnalyzer;

/** Classe Main - Classe responsável pela inicialização do programa Teste
 * @author TRM
 * @version 0.99
 */
public class Main {

    /** Ponto de entrada do analizador sintático
     * @param  args String[] - Argumentos que forem necessários (endereço do
     * arquivo a ser compilado)
     * OBS: No programa teste será definido o caminho manualmente
     */
    public static void main(String[] args) {

        String path = "src/teste.x";

        //Instância o analizador sintático
        SyntacticAnalyser syntacticAnalyser = new SyntacticAnalyser();

        //Instância o analizador léxico
        LexicalAnalyzer lexical = new LexicalAnalyzer(path);

        //Chama o analizador sintático para iniciar a análise
        syntacticAnalyser.parse(lexical);

        System.out.println("Lista de instruções do arquivo " + path.split("/")[1] + ": \n");

        //Percorrer a lista de instruções gerados e imprimi-las na tela
        for (Instruction instruction : syntacticAnalyser.getInstructions()) {

            //Impressão na tela
            System.out.println("Tipo de instrução: " + instruction.getType());
            System.out.println("Inicio -> " + instruction.getStart());
            System.out.println("Final -> " + instruction.getEnd());
        }

        System.out.println("\nOBS.: Os valores de inicio e fim representam a linha"
                + " inicial e final, respectivamente, sempre ignorando os comentários"
                + " e as linhas em branco!");

    }
}
