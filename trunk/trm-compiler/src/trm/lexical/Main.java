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


        String fileText = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("src/serie_fibonnaci.txt")));

            while (reader.ready()) {
                fileText += reader.readLine() + System.getProperty("line.separator");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Recognizer recognizer = new Recognizer(automaton, fileText.toCharArray());
        recognizer.run();

    }
}
