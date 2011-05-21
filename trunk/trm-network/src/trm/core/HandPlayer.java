package trm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Marcos
 */
public class HandPlayer {
    
    public static final int INITIAL_STONES = 7;
    
    private Player player;
    private Set<Stone> stones;
    
    public HandPlayer(Player player, Set<Stone> initialStones) {
        if (initialStones.size() != 7) {
            throw new RuntimeException("Quantidade de peças erradas");
        }
        
        this.player = player;
        stones = new HashSet<Stone>(initialStones);
    }
    
    
    public boolean removeStone(Stone stone) {
        return stones.remove(stone);
    }
    
    public List<Stone> getStones() {
        return Collections.unmodifiableList(new ArrayList<Stone>(stones));
    }
    
    public boolean containsStone(Stone stone) {
        return stones.contains(stone);
    }
    
    public Player getPlayer() {
        return player;
    }
    
    
    
    public static void main(String[] args) {
        Set<Stone> stones = new HashSet<Stone>();
        stones.add(new Stone(SquareNumber.FOUR, SquareNumber.ZERO));
        stones.add(new Stone(SquareNumber.FOUR, SquareNumber.ZERO));
        System.out.println(stones.size());
    }
}
