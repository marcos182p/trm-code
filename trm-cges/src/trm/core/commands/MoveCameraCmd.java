package trm.core.commands;

import trm.core.Camera;
import trm.core.ICommand;

public class MoveCameraCmd implements ICommand {

    private Camera camera;
    private float velocity;

    public MoveCameraCmd(Camera camera, float velocity) {
        this.camera = camera;
        this.velocity = velocity;
    }

    public void execute() {
        this.camera.move(velocity);
    }
}
