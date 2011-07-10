/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.core.lps;

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
}
