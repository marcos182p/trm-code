/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core.commands;

import trm.core.Camera;
import trm.core.Command;

/**
 *
 * @author mpjms
 */
public class MoveCameraHorizontallyCmd implements Command{
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
