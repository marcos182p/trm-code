package component.environment.impl;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers;

    public Subject() {
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
