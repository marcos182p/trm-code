/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.environment.spec.prov;

import java.util.List;

/**
 *
 * @author ttmo
 */
public interface IManager {

    public Object getProvidedInterface(String inter);

    public Object getRequiredInterface(String inter);

    public void setRequiredInterface(String inter, Object objeto);

    public List<String> getProvidedInterfaces();
}
