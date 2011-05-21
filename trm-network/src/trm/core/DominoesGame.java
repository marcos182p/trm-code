package trm.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
    
    public void putLeft(Stone stone, Player player) {
       put(stone, player, 0);
    }
    
    public void putRight(Stone stone, Player player) {
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
    }

    private boolean isPlaying(Player player) {
        return playersQueue.peek().getPlayer().equals(player);
    }

    private HandPlayer createHandPlayer(Player player) {
        //TODO criar mão unica para jogador
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
