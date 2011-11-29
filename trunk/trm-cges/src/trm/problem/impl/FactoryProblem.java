/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.problem.impl;

/**
 *
 * @author ttmo
 */
public class FactoryProblem {

    public static synchronized Manager getManager() {
        return Manager.getInstance();
    }
}
