/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.environment.impl;

/**
 *
 * @author ttmo
 */
public class FactoryEnvironment {

    public static synchronized Manager getManager() {
        return Manager.getManager();
    }
}
