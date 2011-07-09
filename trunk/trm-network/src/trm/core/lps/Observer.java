package trm.core.lps;

import java.lang.reflect.Method;

/**
 *
 * @author Marcos
 */
public interface Observer {
    
    void update(Class source, Method method);
    
    
}
