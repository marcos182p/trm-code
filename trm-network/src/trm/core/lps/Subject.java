package trm.core.lps;

/**
 *
 * @author Marcos
 */
public interface Subject {
    
    void notifyObservers(Event evt);
    
    void registerObserver(Observer o);
}
