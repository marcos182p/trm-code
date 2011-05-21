package trm.core;

import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
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
public class DominoesGameTest {
    
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Dominoes dominoes;
    private DominoesGame dominoesGame;
    
    public DominoesGameTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        init();
    }
    
    @After
    public void tearDown() {
    }
    
    public void init() {
        
        dominoes = Dominoes.getInstance();
        
        player1 = new Player(new PlayerInf(1L, "player1"));
        player2 = new Player(new PlayerInf(2L, "player2"));
        player3 = new Player(new PlayerInf(3L, "player3"));
        player4 = new Player(new PlayerInf(4L, "player4"));
        
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        
        dominoesGame = new DominoesGame(players);
        
    }

//    /**
//     * Test of putLeft method, of class DominoesGame.
//     */
    @Test
    public void testPutLeft() {
        System.out.println("putLeft");
        Stone stone = null;
        Player player = null;
        DominoesGame instance = null;
        instance.putLeft(stone, player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
//
//    /**
//     * Test of putRight method, of class DominoesGame.
//     */
//    @Test
//    public void testPutRight() {
//        System.out.println("putRight");
//        Stone stone = null;
//        Player player = null;
//        DominoesGame instance = null;
//        instance.putRight(stone, player);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getHandPlayer method, of class DominoesGame.
     */
    @Test
    public void testHandPlayer() {
        System.out.println("handPlayer");
        
        HandPlayer handPlayer1 = dominoesGame.getHandPlayer(player1);
        HandPlayer handPlayer2 = dominoesGame.getHandPlayer(player2);
        HandPlayer handPlayer3 = dominoesGame.getHandPlayer(player3);
        HandPlayer handPlayer4 = dominoesGame.getHandPlayer(player4);
        
        //verifica se as mãos dos jogadores estão sendo criadas com a quantidade
        //certa
        assertEquals(handPlayer1.getStones().size(), HandPlayer.INITIAL_STONES);
        assertEquals(handPlayer2.getStones().size(), HandPlayer.INITIAL_STONES);
        assertEquals(handPlayer3.getStones().size(), HandPlayer.INITIAL_STONES);
        assertEquals(handPlayer4.getStones().size(), HandPlayer.INITIAL_STONES);
        
        
        List<Stone> stones = new ArrayList<Stone>();
        
        stones.addAll(handPlayer1.getStones());
        stones.addAll(handPlayer2.getStones());
        stones.addAll(handPlayer3.getStones());
        stones.addAll(handPlayer4.getStones());
        
        //verifica se os jogadores possuem todas as peças divididas da forma
        //correta
        assertTrue(dominoes.getStones().containsAll(stones));
        assertTrue(stones.containsAll(dominoes.getStones()));
        
        
    }
    
}
