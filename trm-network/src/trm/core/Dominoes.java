package trm.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * @author TRM
 * @version 0.99
 */
public class Dominoes {

    private List<Stone> stones;
    
    private static Dominoes instance = new Dominoes();
    
    private Dominoes() {
        stones = new ArrayList<Stone>();
        //gerando as peças
        for (int i = 0; i < 7; i++) {
            SquareNumber up = SquareNumber.values()[i];
            
            for (int j = 0; j < 7; j++) {
                SquareNumber down = SquareNumber.values()[j];
                
                Stone stone = new Stone(up, down);
                
                if (!stones.contains(stone) && !stones.contains(new Stone(down, up))) {
                    stones.add(stone);
                } 
            }
        }
    }
    
    public static Dominoes getInstance() {
        return instance;
    }
    
    public List<Stone> getStones() {
        return Collections.unmodifiableList(new ArrayList<Stone>(stones));
    }
    
}
