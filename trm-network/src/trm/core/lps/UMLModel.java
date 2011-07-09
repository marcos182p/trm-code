package trm.core.lps;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marcos
 */
public class UMLModel {
    
    private static UMLModel instance = new UMLModel();
    
    private Set<Class> entities;
    
    private UMLModel () {
        entities = new HashSet<Class>();
    } 
    
    public static UMLModel getUMLModel() {
        return instance;
    }

    public void register(Class entity) {
        entities.add(entity);
    }
    
    public boolean contains(Class entity) {
        return entities.contains(entity);
    }
    
    public Set<Class> getEntities() {
        return Collections.unmodifiableSet(entities);
    }
}
