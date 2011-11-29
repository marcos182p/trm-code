package trm.core.commands;

import trm.core.Camera;
import trm.core.ICommand;

public class MoveCameraHorizontallyCmd implements ICommand {

    private Camera camera;
    private float velocity;

    public MoveCameraHorizontallyCmd(Camera camera, float velocity) {
        this.camera = camera;
        this.velocity = velocity;
    }

    public void execute() {
        camera.moveHorizontally(velocity);
    }
}
