/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package component.problem.impl;

/**
 *
 * @author ttmo
 */
public class FactoryProblem {

    public static synchronized Manager getManager() {
        return Manager.getInstance();
    }
}
