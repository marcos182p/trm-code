package trm.core;

import java.util.HashSet;
import java.util.Set;
import trm.core.lps.Event;
import trm.core.lps.Observer;
import trm.core.lps.Subject;

/**
 *
 * @author Marcos
 */
public class GameEntity implements Subject {
    
    private Set<Observer> observers = new HashSet<Observer>();

    @Override
    public void notifyObservers(Event evt) {
        for (Observer observer: observers) {
            observer.update(evt);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    
}
