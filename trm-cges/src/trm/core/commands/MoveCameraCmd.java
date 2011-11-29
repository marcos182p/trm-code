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
public class MoveCameraCmd implements Command{
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
