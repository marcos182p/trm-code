/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.lexical.rules;

/**
 *
 * @author thyago
 */
public class IsSymbolRule implements IRule {

    char[] simbolos = {'_', '+', '-', '=', '[', ']', '{', '}', '^', '~', '`', '´', ',', '”', '.',
        ';', ':', '/', '?', '\\', '\'', '¬', '¹', '²', '³', '£', '¢', '§', 'ª', 'º', '°', '·', '!',
        '@', '#', '$', '%', '&', '*', '(', ')'};

    @Override
    public boolean evaluate(char transitionChar) {

        for (int i = 0; i < simbolos.length; i++) {
            if (transitionChar == simbolos[i]) {
                return true;
            }
        }

        return false;
    }
}