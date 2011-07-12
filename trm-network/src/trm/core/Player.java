package trm.core;

import trm.core.lps.Event;
import trm.core.lps.Observer;
import trm.core.lps.ObserverManager;

/**
 * @author TRM
 * @version 0.99
 */
public class Player extends GameEntity {

    private PlayerInf inf;

    public Player() {
        //para o gson.
    }
    
    public Player(PlayerInf inf) {
        
        this.inf = inf;
        
        Observer observer = ObserverManager.getObserverManager().get(this);
        registerObserver(observer);
        
        notifyObservers(Event.CREATION);
    }

    public PlayerInf getInf() {
        
        return inf;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.inf != other.inf && (this.inf == null || !this.inf.equals(other.inf))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.inf != null ? this.inf.hashCode() : 0);
        return hash;
    }
}
