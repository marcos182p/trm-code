package trm.net.model;

import java.util.List;
import trm.core.Player;
import trm.core.PlayerInf;

/**
 *
 * @author Marcos
 */
public class PlayerManager {
    
    private static PlayerManager instance = new PlayerManager();
    private List<Player> players;
    private long lastId = 0L;
    
    private PlayerManager() {
        
    }
    
    public static PlayerManager getPlayerManager() {
        return instance;
    }
    
    public Player newPlayer(String name) {
        Player player = new Player(new PlayerInf(lastId, name));
        players.add(player);
        return player;
    }
    
    public boolean removePlayer(Player player) {
        //FIXME caso ele pertença a uma sala de jogo notifica ela.
        return players.remove(player);
    }
}
