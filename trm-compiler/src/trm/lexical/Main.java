/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.lexical;

/**
 *
 * @author Thyago
 */
public class Main {

    public static void main(String[] args) {

        Automaton automaton = CreateAutomaton.create();


        //Test


//        String texto = "";
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(new File("teste.trm")));
//
//            while (reader.ready()) {
//                texto += reader.readLine() + System.getProperty("line.separator");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //String arquivo = " false true  1.1 d123456 1212d /***aqui também*/ enquanto 3 //se 22\n   3hhg /***Isto é um comentário e deve ser ignorado*/ asdasds 123asd blá ";



        String arquivo = "() {} [] + - * mod not or and > < = ; :  >= <=  = !=   <-";


        Recognizer recognizer = new Recognizer(automaton, arquivo.toCharArray());
        recognizer.run();

    }
}
