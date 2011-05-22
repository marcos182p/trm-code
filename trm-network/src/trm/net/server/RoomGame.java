package trm.net.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import trm.core.DominoesGame;
import trm.core.Player;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public class RoomGame {

    private Long id;
    private String roomName;
    private DominoesGame dominoesGame;
    private Set<ServerTask> tasks;
    private boolean started;
    
    public RoomGame(Long id, String roomName) {
        this.id = id;
        this.roomName = roomName;
        tasks = new HashSet<ServerTask>();
        started = false;
    }

    public Long getId() {
        return id;
    }

    public String getRoomGame() {
        return roomName;
    }
    
    public DominoesGame getDominoesGame() {
        return dominoesGame;
    }
    
    public void startGame() {
        
        if (started) return;
        
        List<Player> players = new ArrayList<Player>();
        for (ServerTask task : tasks) {
            players.add(task.getPlayer());
            task.getPlayer().setState(StatePlayer.PLAYING);
        }

        dominoesGame = new DominoesGame(players);
        started = true;
    }
    
    public void stopGame() {
        
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
        stopGame();
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
    
    Set<ServerTask> getServerTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public void broadcast(ResponseServer message) throws IOException {
        for (ServerTask user : tasks) {
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
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
