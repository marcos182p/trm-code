package trm.net.server.game;

import java.util.HashMap;
import java.util.Map;
import trm.core.lps.ObserverImpl;
import trm.net.server.ServerTask;

/**
 *
 * @author Marcos
 */
public class ObserverManager {
    
    private static ObserverManager instance = new ObserverManager();
    
    private Map<ServerTask, ObserverImpl> observers;
    
    private ObserverManager () {
        observers = new HashMap<ServerTask, ObserverImpl>();
    }
    
    public void register(ServerTask task, ObserverImpl observer) {
        observers.put(task, observer);
    }
}
