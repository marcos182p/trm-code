/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mpjms
 */
public class Controller {
    private boolean keyPressed[];
    private Map<Integer, Command> commandMap;

    public Controller() {
        keyPressed = new boolean[256];
        commandMap = new HashMap<Integer, Command>();
    }

    public void keyPressed(int key) {
        keyPressed[key] = true;
    }
    public void keyReleased(int key) {
        keyPressed[key] = false;
    }
    public void addCommand(int key, Command command) {
        commandMap.put(key, command);
    }
    public void removeCommand(int key) {
        commandMap.remove(key);
    }
    public void update() {
        for(int i = 0; i < 256; i++) {
            if(keyPressed[i] && commandMap.containsKey(i)) {
                commandMap.get(i).execute();
            }
        }
    }
}
