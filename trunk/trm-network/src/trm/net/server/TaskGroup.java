package trm.net.server;

import trm.net.model.GroupNet;

/**
 *
 */
public class TaskGroup implements Runnable {
    
    private GroupNet group;
    
    public TaskGroup(GroupNet group) {
        this.group = group;
    }
    
    @Override
    public void run() {
        
    }
    
}
