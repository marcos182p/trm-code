package trm.core.commands;

import trm.core.Camera;
import trm.core.ICommand;

public class TurnCameraCmd implements ICommand {

    private Camera camera;
    private float angle;

    public TurnCameraCmd(Camera camera, float angle) {
        this.camera = camera;
        this.angle = angle;
    }

    public void execute() {
        camera.turn(angle);
    }
}
