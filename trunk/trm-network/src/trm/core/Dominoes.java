package trm.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Dominoes {
    
    private Set<Stone> stones;
    
    private static Dominoes instance = new Dominoes();
    
    private Dominoes() {
        stones = new HashSet<Stone>();
        
        for (int i = 0; i < 7; i++) {
            SquareNumber up = SquareNumber.values()[i];
            
            for (int j = 0; j < 7; j++) {
                SquareNumber down = SquareNumber.values()[j];
                
                Stone stone = new Stone(up, down);
                
                if (!stones.contains(stone)) {
                    stones.add(stone);
                } 
            }
        }
    }
    
    public static Dominoes getInstance() {
        return instance;
    }
    
    public Set<Stone> getStones() {
        return Collections.unmodifiableSet(stones);
    }
    
}
