package trm.core.commands;

import trm.core.Camera;
import trm.core.ICommand;

public class MoveCameraVerticallyCmd implements ICommand {

    private Camera camera;
    private float velocity;

    public MoveCameraVerticallyCmd(Camera camera, float velocity) {
        this.camera = camera;
        this.velocity = velocity;
    }

    public void execute() {
        camera.moveVertically(velocity);
    }
}
