package br.ufal.ic.server;

import br.ufal.ic.model.GroupNet;

/**
 *
 * @author Marcos Paulo
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
