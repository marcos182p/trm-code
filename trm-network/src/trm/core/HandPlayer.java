package trm.core;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Marcos
 */
public class HandPlayer {
    
    private static final int INITIAL_STONES = 7;
    
    private Player player;
    private Set<Stone> stones;
    
    public HandPlayer(Set<Stone> initialStones) {
        if (initialStones.size() != 7) {
            throw new RuntimeException("Quantidade de peças erradas");
        }
        
        stones = new HashSet<Stone>(initialStones);
    }
    
    
    public boolean removeStone(Stone stone) {
        return stones.remove(stone);
    }
    
    public boolean containsStone(Stone stone) {
        return stones.contains(stone);
    }
    
    public Player getPlayer() {
        return player;
    }
}
