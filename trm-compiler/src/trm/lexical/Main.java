/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.lexical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Thyago
 */
public class Main {

    public static void main(String[] args) {

        Automaton automaton = CreateAutomaton.create();


        //File test


        String file = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("C:/Users/Douglas/Documents/NetBeansProjects/trm-code/trm-compiler/src/serie_fibonnaci.txt")));

            while (reader.ready()) {
                file += reader.readLine() + System.getProperty("line.separator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Recognizer recognizer = new Recognizer(automaton, file.toCharArray());
        recognizer.run();

    }
}
