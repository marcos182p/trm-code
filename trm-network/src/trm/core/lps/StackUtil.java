/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.core.lps;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class StackUtil {

    public static void printStack() {
        Throwable thr = new Throwable();
        thr.fillInStackTrace();
        thr.printStackTrace();

        StackTraceElement[] ste = thr.getStackTrace();
    }

    public static List<StackTraceElement> filterStack(
            StackTraceElement[] elements) {

        UMLModel model = UMLModel.getUMLModel();

        List<StackTraceElement> stack = new ArrayList<StackTraceElement>();

        for (StackTraceElement element : elements) {
            if (model.contains(element.getClassName())) {
                stack.add(element);
            }
        }

        return stack;
    }
}
