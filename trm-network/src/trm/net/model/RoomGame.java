package trm.net.model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import trm.core.DominoesGame;
import trm.net.model.protocol.ResponseServer;

/**
 *
 */
public class RoomGame {
    
    private Long id;
    
    private DominoesGame dominoesGame;
    private Set<ServerTask> tasks;
    
    public RoomGame(Long id) {
        this.id = id;
        tasks = new HashSet<ServerTask>();
    }
    
    public Long getId() {
        return id;
    }
    
    public void putServerTask(ServerTask user) {
        tasks.add(user);
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
