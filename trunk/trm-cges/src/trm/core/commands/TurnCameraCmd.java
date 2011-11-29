/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core.commands;

import trm.core.Camera;
import trm.core.Command;

/**
 *
 * @author jmb
 */
public class TurnCameraCmd implements Command{
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
