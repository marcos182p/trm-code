package trm.core.lps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Marcos
 */
public class ObserverImpl implements Observer {
    
    private List<Call> calls;
    
    private UMLModel model;
    
    public ObserverImpl() {
        calls = new ArrayList<Call>();
        model = UMLModel.getUMLModel();
    }
    
    public void reset() {
        calls.clear();
    }
    
    public List<Call> getCalls() {
        return Collections.unmodifiableList(calls);
    }
    
    @Override
    public void update(Class source, Method method) {
        if (!model.contains(source)) {
            throw new RuntimeException("entidade chamadara não registrada");
        }
        Call call = new Call(source, method);
        System.out.println(call);
        calls.add(call);
        
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObserverImpl other = (ObserverImpl) obj;
        if (this.calls != other.calls && (this.calls == null
                || !this.calls.equals(other.calls))) {
            return false;
        }
        if (this.model != other.model && (this.model == null
                || !this.model.equals(other.model))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (this.calls != null ? this.calls.hashCode() : 0);
        hash = 19 * hash + (this.model != null ? this.model.hashCode() : 0);
        return hash;
    }

    
}
