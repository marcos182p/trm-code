package trm.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private enum Position {

        LEFT, RIGHT
    }
    //constantes
    public static final int MAX_PLAYER = 4;
    public static final int MIN_PLAYER = 2;
    
    private List<Stone> gameStones;
    private Queue<HandPlayer> playersQueue;
    private Map<Player, HandPlayer> playersMap;
    
    private Player winner;
    
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
        
        this.gameStones = new ArrayList<Stone>();
        this.winner = null;
    }
    
    public Player getWinner() {
        return winner;
    }

    public List<Stone> getBoardStones() {
        return Collections.unmodifiableList(gameStones);
    }
    /**
     * remove um jogador do jogo
     */
    public void removePlayer(Player player) {
        HandPlayer hand = null;
        for (HandPlayer h: playersQueue) {
            if (player.equals(h.getPlayer())) {
                hand = h;
                break;
            }
                
        }
        playersQueue.remove(hand);
    }
    
    
    //TODO refatorar!
    public void putLeft(Stone stone, Player player) {
        
        if (!isValidPlayed(stone.getSquareRight(), Position.LEFT)) {
            if (!isValidPlayed(stone.getSquareLeft(), Position.LEFT)) {
                throw new RuntimeException("Impossivel colocar peça");
            }
            stone = new Stone(stone.getSquareRight(), stone.getSquareLeft());
        }


       put(stone, player, 0);
    }
    
    //TODO refatorar!
    public void putRight(Stone stone, Player player) {
        
        if (!isValidPlayed(stone.getSquareLeft(), Position.RIGHT)) {
            if (!isValidPlayed(stone.getSquareRight(), Position.RIGHT)) {
                throw new RuntimeException("Impossivel colocar peça");
            }
            stone = new Stone(stone.getSquareRight(), stone.getSquareLeft());
        }
        
        put(stone, player, gameStones.size() - 1);
    }
    
    private boolean isValidPlayed(SquareNumber square, Position position) {
        
        if (gameStones.isEmpty()) {
            return true;
        }
        
        boolean result = false;
        
        Stone currentStone = null;
        
        switch (position) {
            case LEFT:
                currentStone = gameStones.get(0);
                result = currentStone.getSquareRight().equals(square);
                break;
            case RIGHT:
                currentStone = gameStones.get(gameStones.size() - 1);
                result = currentStone.getSquareLeft().equals(square);
                break;
        }
        
        return result;
    }
    
    public HandPlayer getHandPlayer(Player player) {
        return playersMap.get(player);
    }

    private void put(Stone stone, Player player, int position) {
        
        if (winner != null) {
            throw new RuntimeException("o jogo ja tem um vencendor");
        }
        
        if (!isPlaying(player)) {
            throw new RuntimeException("Não é a vez desse jogador");
        }

        if (gameStones.contains(stone)) {
            throw new RuntimeException("Peça ja está no jogo");
        }
        
        if (!getHandPlayer(player).containsStone(stone)) {
            throw new RuntimeException("Peça não pertencente ao jogador");
        }

        gameStones.add(position, stone);
        getHandPlayer(player).removeStone(stone);
        playersQueue.poll();
        playersQueue.add(getHandPlayer(player));
        
        if (getHandPlayer(player).getStones().isEmpty()) {
            winner = player;
        }
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();

        for (HandPlayer hand: playersQueue) {
            players.add(hand.getPlayer());
        }
        return players;
    }

    private boolean isPlaying(Player player) {
        return playersQueue.peek().getPlayer().equals(player);
    }
    
    private Set<Stone> stonesUseds = new HashSet<Stone>();
    
    /**
     * Cria mão aleatoria para jogador
     */
    private HandPlayer createHandPlayer(Player player) {

        Dominoes dominoes = Dominoes.getInstance();
        
        Random random = new Random();
        Set<Stone> stones = new HashSet<Stone>();
        while (stones.size() < HandPlayer.INITIAL_STONES) {

            Stone stone = dominoes.getStones().get(random.nextInt(dominoes.getStones().size()));
            Stone inverseStone = new Stone(stone.getSquareRight(), stone.getSquareLeft());
            
            
            if (!stonesUseds.contains(stone) && !stonesUseds.contains(inverseStone)) {
                if (!stones.contains(stone) && !stones.contains(inverseStone)) {
                    stones.add(stone);
                    stonesUseds.add(stone);
                }
            }
        }

        return new HandPlayer(player, stones);
    }
}