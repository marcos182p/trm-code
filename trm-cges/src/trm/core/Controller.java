package trm.core;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private boolean keyPressed[];
    private Map<Integer, ICommand> commandMap;

    public Controller() {
        keyPressed = new boolean[256];
        commandMap = new HashMap<Integer, ICommand>();
    }

    public void keyPressed(int key) {
        keyPressed[key] = true;
    }

    public void keyReleased(int key) {
        keyPressed[key] = false;
    }

    public void addCommand(int key, ICommand command) {
        commandMap.put(key, command);
    }

    public void removeCommand(int key) {
        commandMap.remove(key);
    }

    public void update() {
        for (int i = 0; i < 256; i++) {
            if (keyPressed[i] && commandMap.containsKey(i)) {
                commandMap.get(i).execute();
            }
        }
    }
}
