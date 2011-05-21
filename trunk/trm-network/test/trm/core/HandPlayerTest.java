package trm.core;

import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos
 */
public class HandPlayerTest {
    
    private final Dominoes dominoes = Dominoes.getInstance();
    
    public HandPlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of removeStone method, of class HandPlayer.
     */
    @Test
    public void testStone() {
        System.out.println("stone");
        
        Player player = new Player(new PlayerInf(1L, "user-test"));
        Set<Stone> stones = new HashSet<Stone>();
        
        for (int i = 0; i < 6; i++) {
            stones.add(dominoes.getStones().get(i));
        }
        
        try {

            HandPlayer hand = new HandPlayer(player, stones);
            fail("HandPlaer aceitou uma quantidade de peças inferior ao "
                    + "permitido");
            
        } catch (Exception e) {
        }
        
        
    }

}
