package trm.net.server.game;

/**
 *
 */
public class RoomInf {
    public Long id;
    public String roomName;
    public boolean started;

    public RoomInf() {
    }

    public RoomInf(Long id, String roomName, boolean started) {
        this.id = id;
        this.roomName = roomName;
        this.started = started;
    }
    
}
