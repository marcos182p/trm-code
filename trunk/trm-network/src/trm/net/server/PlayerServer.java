package trm.net.server;

import java.util.HashSet;
import java.util.Set;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.net.model.EventType;
import trm.net.model.Observer;
import trm.net.model.Subject;

/**
 *
 * @author Marcos
 */
public class PlayerServer extends Player implements Subject {

    private StatePlayer state;
    private Set<Observer> observers;

    public PlayerServer(PlayerInf inf) {
        super(inf);
        state = StatePlayer.NO_PLAYING;
        observers = new HashSet<Observer>();
    }

    public StatePlayer getState() {
        return state;
    }

    public void setState(StatePlayer state) {
        this.state = state;
        //FIXME noficar quando mudar de estado
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(EventType event) {
        for (Observer observer : observers) {
            observer.updade(event);
        }
    }
}
