package trm.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

/**
 *
 */
public class DominoesGame {
    
    //constantes
    private static final int MAX_PLAYER = 4;
    private static final int MIN_PLAYER = 2;
    
    private List<Stone> currentStones;
    private Queue<HandPlayer> playersQueue;
    private Map<Player, HandPlayer> playersMap;
    
    
    public static void main(String[] args) {
        Player player1 = new Player(new PlayerInf(1L, "mp1"));
        Player player2 = new Player(new PlayerInf(2L, "mp2"));
        Player player3 = new Player(new PlayerInf(3L, "mp3"));
        Player player4 = new Player(new PlayerInf(4L, "mp4"));
        
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        
        
        
        DominoesGame game = new DominoesGame(players);
        
//        for (int i = 0; i < players.size(); i++) {
//            
//            HandPlayer hand = game.getHandPlayer(players.get(i));
//            
//            
//            String temp = "";
//            
//            for (Stone stone : hand.getStones()) {
//                temp += stone.getSquareLeft() + ":" + stone.getSquareRight() + "-";
//            }
//            System.out.println("player = " + hand.getPlayer().getInf().getNickName());
//            System.out.println(temp);
//        }
        
    }
    
    public DominoesGame(List<Player> players) {
        if (players.size() > MAX_PLAYER || players.size() < MIN_PLAYER) {
            throw new RuntimeException("Numero de jogadores não permitidos.");
        }
        this.playersQueue = new ArrayDeque<HandPlayer>();
        this.playersMap = new HashMap<Player, HandPlayer>();
        
        for (Player player: players) {
            HandPlayer hand = createHandPlayer(player);
            playersQueue.add(hand);
            playersMap.put(player, hand);
        }
        
        this.currentStones = new ArrayList<Stone>();
    }
    
    //TODO refatorar
    public void putLeft(Stone stone, Player player) {
        
        if (currentStones.size() > 0) {

            Stone leftStone = currentStones.get(0);
            SquareNumber squareLeft = leftStone.getSquareLeft();

            if (!squareLeft.equals(stone.getSquareRight())) {
                if (!squareLeft.equals(stone.getSquareLeft())) {
                    throw new RuntimeException("Impossivel colocar peça");
                }
                stone = new Stone(stone.getSquareRight(), stone.getSquareLeft());
            }

        }
       
       put(stone, player, 0);
    }
    
    //TODO refatorar
    public void putRight(Stone stone, Player player) {
        if (currentStones.size() > 0) {

            Stone rightStone = currentStones.get(currentStones.size() - 1);
            SquareNumber squareRight = rightStone.getSquareRight();

            if (!squareRight.equals(stone.getSquareLeft())) {
                if (!squareRight.equals(stone.getSquareRight())) {
                    throw new RuntimeException("Impossivel colocar peça");
                }
                stone = new Stone(stone.getSquareRight(), stone.getSquareLeft());
            }

        }
        
        put(stone, player, currentStones.size() - 1);
    }
    
    public HandPlayer getHandPlayer(Player player) {
        return playersMap.get(player);
    }

    private void put(Stone stone, Player player, int position) {
        
        if (!isPlaying(player)) {
            throw new RuntimeException("Não é a vez desse jogador");
        }

        if (currentStones.contains(stone)) {
            throw new RuntimeException("Peça ja está no jogo");
        }
        
        if (!getHandPlayer(player).containsStone(stone)) {
            throw new RuntimeException("Peça não pertencente ao jogador");
        }

        currentStones.add(position, stone);
        getHandPlayer(player).removeStone(stone);
        playersQueue.poll();
        playersQueue.add(getHandPlayer(player));
    }

    private boolean isPlaying(Player player) {
        return playersQueue.peek().getPlayer().equals(player);
    }
    
    private Set<Stone> stonesUseds = new HashSet<Stone>();
    
    //TODO criar mão unica para jogador
    private HandPlayer createHandPlayer(Player player) {

        Dominoes dominoes = Dominoes.getInstance();
        
        Random random = new Random();
        Set<Stone> stones = new HashSet<Stone>();
        
        while (stones.size() < HandPlayer.INITIAL_STONES) {
            
            Stone stone = dominoes.getStones().get(random.nextInt(dominoes.getStones().size()));

            if (!stonesUseds.contains(stone)) {
                if (!stones.contains(stone)) {
                    stones.add(stone);
                    stonesUseds.add(stone);
                }
            }
        }

        return new HandPlayer(player, stones);
    }
}
