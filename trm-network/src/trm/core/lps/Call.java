package trm.core.lps;

import java.lang.reflect.Method;

/**
 *
 * @author Marcos
 */
public class Call {

    private Class source;
    private Method method;

    public Call(Class source, Method method) {
        this.source = source;
        this.method = method;
    }
    
    public Class getSource() {
        return source;
    }
    
    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "Call{" + "source=" + source + ", method=" + method + '}';
    }
}
