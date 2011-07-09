package trm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 
 * @author TRM
 * @version 0.99
 */
public class HandPlayer extends GameEntity {
    
    public static final int INITIAL_STONES = 7;
    
    private Player player;
    private Set<Stone> stones;
    private int score;
    
    public HandPlayer(Player player, Set<Stone> initialStones) {
        if (initialStones.size() != 7) {
            throw new RuntimeException("Quantidade de peças erradas");
        }
        
        this.player = player;

        stones = new HashSet<Stone>(initialStones);
        
        score = 0;
        for (Stone stone: stones) {
            score += stone.getSquareLeft().ordinal();
            score += stone.getSquareRight().ordinal();

        }
    }
    
    
    boolean removeStone(Stone stone) {
        Stone inverted = new Stone(
                stone.getSquareRight(),
                stone.getSquareLeft());

        boolean result = stones.remove(stone) || stones.remove(inverted);

        if (result) {
            score -= stone.getSquareLeft().ordinal();
            score -= stone.getSquareRight().ordinal();
        }
        return result;
    }
    
    public List<Stone> getStones() {
        notifyObservers(findMethod(this, "getStones"));
        
        return Collections.unmodifiableList(new ArrayList<Stone>(stones));
    }

    public int getScore() {
        notifyObservers(findMethod(this, "getScore"));
        
        return score;
    }
    
    // TODO Fazer verificação de uma forma melhor.
    public boolean containsStone(Stone stone) {
        notifyObservers(findMethod(this, "containsStone", Stone.class));
        
        for (Stone s : stones) {
            if (s.equals(stone)) {
                return true;
            }
            if (s.equals(new Stone(stone.getSquareRight(), 
                    stone.getSquareLeft()))) {
                
                return true;
            }
        }
        return false;
    }
    
    public Player getPlayer() {
        notifyObservers(findMethod(this, "getPlayer"));
        
        return player;
    }
    
    
    
    public static void main(String[] args) {
        Set<Stone> stones = new HashSet<Stone>();
        stones.add(new Stone(SquareNumber.FOUR, SquareNumber.ZERO));
        stones.add(new Stone(SquareNumber.FOUR, SquareNumber.ZERO));
        System.out.println(stones.size());
    }
}
