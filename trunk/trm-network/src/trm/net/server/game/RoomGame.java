package trm.net.server.game;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import trm.core.DominoesGame;
import trm.core.Movement;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.model.protocol.ResponseServer;
import trm.net.server.ServerTask;

/**
 * @author TRM
 * @version 0.99
 */
public class RoomGame {

    private String roomName;
    private DominoesGame dominoesGame;
    private Queue<ServerTask> tasks;
    private boolean started;
    
    public RoomGame(String roomName) {
        this.roomName = roomName;
        tasks = new ArrayDeque<ServerTask>();
        started = false;
    }
    
    public RoomInf getRoomInf() {
        return new RoomInf(roomName, tasks.size(), started);
    }


    public String getRoomGame() {
        return roomName;
    }

    public List<Stone> getHandPlayer(Player player) {

        if (!isStarted()) {
            throw new RuntimeException("Jogo não iniciado");
        }
        
        return dominoesGame.getHandPlayer(player).getStones();
    }
    
    public List<Stone> getBoardStones() {
        
        if (!isStarted()) {
            throw new RuntimeException("Jogo não iniciado");
        }
        
        return dominoesGame.getBoardStones();
    }
    
    void putLeft(Stone stone, ServerTask player) {
        runMovement(new Movement(stone, Movement.Action.PUT_LEFT), player);
    }
    
    void putRight(Stone stone, ServerTask player) {
        
        runMovement(new Movement(stone, Movement.Action.PUT_RIGHT), player);
    }
    
    void putPass(ServerTask player) {
        runMovement(new Movement(null, Movement.Action.PASS), player);
    }
    
    void runMovement(Movement movement, ServerTask task) {
        if (!isStarted()) {
            throw new RuntimeException("jogo não iniciado.");
        }
        
        if (!tasks.contains(task)) {
            throw new RuntimeException("jogador não pertencente a essa sala");
        }
        
        switch (movement.action) {
            case PUT_LEFT:
                dominoesGame.putLeft(movement.stone, task.getPlayer());
                break;
            case PUT_RIGHT:
                dominoesGame.putRight(movement.stone, task.getPlayer());
                break;
            case PASS:
                dominoesGame.putPass(task.getPlayer());
                break;
        }
    }

    private ServerTask getOwner() {
        return tasks.peek();
    }
    
    void startGame(ServerTask owner) {

        if (started) {
            throw new RuntimeException("jogo na sala : " + roomName +
                    " já em execução");
        }

        if (!getOwner().equals(owner)) {
            throw new RuntimeException("usario sem permição de iniciar o jogo");
        }
        

        List<Player> players = new ArrayList<Player>();
        for (ServerTask task : tasks) {
            players.add(task.getPlayer());
            task.getPlayer().setState(StatePlayer.PLAYING);
        }

        try {

            dominoesGame = new DominoesGame(players);
            started = true;
        } catch (Exception e) {
            resetPlayers();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    //TODO nem todos os usarios podem dar o stopGame
    void stopGame() {
        
        if (!started) return;
        resetPlayers();
        started = false;
//        dominoesGame = null;
    }


    public PlayerInf getWinner() {
        return dominoesGame.getWinner();
    }

    private void resetPlayers() {
        for (ServerTask t : tasks) {
            t.getPlayer().setState(StatePlayer.NO_PLAYING);
        }
    }

    public boolean isStarted() {
        return started;
    }

    void removeServerTask(ServerTask task) {
        if (!tasks.contains(task)) {
            throw new RuntimeException("task não pertecente a essa sala");
        }
        if (dominoesGame != null) {
            dominoesGame.removePlayer(task.getPlayer());
        }
        tasks.remove(task);
    }

    void putServerTask(ServerTask task) {
        if (tasks.size() >= DominoesGame.MAX_PLAYER) {
            throw new RuntimeException("Numero maximo de jogadores atigindos");
        }

        if (started) {
            throw new RuntimeException("jogo já esta em execução");
        }

        tasks.add(task);
    }
    //TODO refatorar
    List<PlayerInf> getPlayers() {
        
        List<PlayerInf> players = new ArrayList<PlayerInf>();
        if (!isStarted()) {
            for (ServerTask task : tasks) {
                PlayerInf pi = task.getPlayer().getInf();
                players.add(new PlayerInf(pi.getId(), pi.getNickName(),
                        getOwner().equals(task)));
            }
            return players;
        }

        for (Player player : dominoesGame.getPlayers()) {

            PlayerInf pi = player.getInf();
            players.add(new PlayerInf(pi.getId(), pi.getNickName(),
                    getOwner().getPlayer().equals(player)));
        }

        return players;
    }

    void broadcast(ResponseServer message, ServerTask... excluded) {
        List<ServerTask> excludedList = Arrays.asList(excluded);
        try {
            for (ServerTask user : tasks) {
                if (excludedList.contains(user)) {
                    continue;
                }

                user.sendMessage(message);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RoomGame other = (RoomGame) obj;
        if ((this.roomName == null) ? (other.roomName != null) : !this.roomName.equals(other.roomName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.roomName != null ? this.roomName.hashCode() : 0);
        return hash;
    }
}
