package trm.net.server;

import trm.net.model.RoomGame;

/**
 *
 */
public class TaskGroup implements Runnable {
    
    private RoomGame group;
    
    public TaskGroup(RoomGame group) {
        this.group = group;
    }
    
    @Override
    public void run() {
        
    }
    
}
