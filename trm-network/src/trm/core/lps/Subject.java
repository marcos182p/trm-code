package trm.core.lps;

import java.lang.reflect.Method;

/**
 *
 * @author Marcos
 */
public interface Subject {
    
    void notifyObservers(Method method);
    
    void register(Observer... observers);
    
}
