package trm.net.server.game;

/**
 * @author TRM
 * @version 0.99
 */
public class RoomInf {
    
    public String roomName;
    public Integer players;
    public boolean started;

    public RoomInf() {
    }

    public RoomInf(String roomName, Integer players, boolean started) {
        this.roomName = roomName;
        this.players = players;
        this.started = started;
    }

    
}
