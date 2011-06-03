package trm.net.server.game;

/**
 *
 */
public class RoomInf {
    public Long id;
    public String roomName;
    public Integer players;
    public boolean started;

    public RoomInf() {
    }

    public RoomInf(Long id, String roomName, Integer players, boolean started) {
        this.id = id;
        this.roomName = roomName;
        this.players = players;
        this.started = started;
    }

    
}
