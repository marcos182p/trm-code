package trm.core;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.core.lps.Observer;
import trm.core.lps.Subject;

/**
 *
 * @author Marcos
 */
public abstract class GameEntity implements Subject {
    
    protected Set<Observer> observers = new HashSet<Observer>();

    protected Method findMethod(Object source, String invokedMethod, Class<?>... parameterTypes) {
        Method method = null;

        try {
            method = source.getClass().getMethod(invokedMethod, parameterTypes);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GameEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GameEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return method;
    }
    
    protected Method findDeclaredMethod(Object source, String invokedMethod, Class<?>... parameterTypes) {
        Method method = null;

        try {
            method = source.getClass().getDeclaredMethod(invokedMethod, 
                    parameterTypes);
            
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GameEntity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GameEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        return method;
    }
    
    @Override
    public void register(Observer... observers) {
        this.observers.addAll(Arrays.asList(observers));
    }
    
    protected void register(Set<Observer> observers) {
        this.observers.addAll(observers);
    }

    @Override
    public void notifyObservers(Method method) {
        if (method == null) return;
        
        for (Observer observer : observers) {
            observer.update(this.getClass(), method);
        }
    }
    
}
