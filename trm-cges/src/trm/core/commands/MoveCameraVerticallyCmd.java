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
public class MoveCameraVerticallyCmd implements Command{
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
