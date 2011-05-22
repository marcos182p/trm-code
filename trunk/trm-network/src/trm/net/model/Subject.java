package trm.net.model;

/**
 *
 */
public interface Subject {

    void addObserver(Observer observer);

    void notifyObservers(EventType event);
}
