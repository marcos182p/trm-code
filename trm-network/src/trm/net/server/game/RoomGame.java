package trm.net.server.game;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import trm.core.DominoesGame;
import trm.core.Player;
import trm.core.PlayerInf;
import trm.core.Stone;
import trm.net.model.protocol.ResponseServer;
import trm.net.server.ServerTask;

/**
 *
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
        
        if (!isValidPlay(player)) {
            throw new RuntimeException("jogador não pertencente a essa sala");
        }
        dominoesGame.putLeft(stone, player.getPlayer());
    }
    
    void putRight(Stone stone, ServerTask player) {
        if (!isValidPlay(player)) {
            throw new RuntimeException("jogador não pertencente a essa sala");
        }
        dominoesGame.putRight(stone, player.getPlayer());
    }
    
    
    private boolean isValidPlay(ServerTask task) {
        return tasks.contains(task) && isStarted();
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

        dominoesGame = new DominoesGame(players);
        started = true;
    }
    
    //TODO nem todos os usarios podem dar o stopGame
    void stopGame() {
        
        if (!started) return;
        
        for (ServerTask t : tasks) {
            t.getPlayer().setState(StatePlayer.NO_PLAYING);
        }
        started = false;
        dominoesGame = null;
    }

    public boolean isStarted() {
        return started;
    }

    void removeServerTask(ServerTask task) {
        if (!tasks.contains(task)) {
            throw new RuntimeException("task não pertecente a essa sala");
        }
        dominoesGame.removePlayer(task.getPlayer());
        tasks.remove(task);
    }

    void putServerTask(ServerTask task) {
        if (tasks.size() > DominoesGame.MAX_PLAYER) {
            throw new RuntimeException("Numero maximo de jogadores atigindos");
        }

        if (started) {
            throw new RuntimeException("jogo já esta em execução");
        }

        tasks.add(task);
    }
    
    List<PlayerInf> getPlayers() {
        
        List<PlayerInf> players = new ArrayList<PlayerInf>();

        for (ServerTask task: tasks) {
            players.add(task.getPlayer().getInf());
        }
        return players;
    }

    void broadcast(ResponseServer message, ServerTask... excluded) throws IOException {
        List<ServerTask> excludedList = Arrays.asList(excluded);
    
        for (ServerTask user : tasks) {
            if (excludedList.contains(user)) continue;
            
            user.sendMessage(message);
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
