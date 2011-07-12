package trm.core.lps;

import java.util.HashMap;
import java.util.Map;
import trm.core.Player;

/**
 *
 * @author Marcos
 */
public class ObserverManager {
    
    private Map<Player, Observer> observers;
    
    private static ObserverManager instance = new ObserverManager();
    
    private ObserverManager() {
        observers = new HashMap<Player, Observer>();
    }
    
    public static ObserverManager getObserverManager() {
        return instance;
    }
    
    public ObserverImpl get(Player player) {
        if (observers.get(player) == null) {
            observers.put(player, new ObserverImpl());
        }
        return (ObserverImpl) observers.get(player);
    }
    
    
}
