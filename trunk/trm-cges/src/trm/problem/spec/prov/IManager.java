/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.problem.spec.prov;

import java.util.List;

/**
 *
 * @author ttmo
 */
public interface IManager {

    public<E> E getProvidedInterface(Class<E> classType);

    public Object getRequiredInterface(String inter);

    public void setRequiredInterface(String inter, Object objeto);

    public List<String> getProvidedInterfaces();
}
