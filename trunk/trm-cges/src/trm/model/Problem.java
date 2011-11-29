/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.model;

/**
 *
 * @author ttmo
 */
public class Problem {

    private Vector source;
    private Vector target;
    private float gravity;

    public Problem(Vector source, Vector target, float gravity) {
        this.source = source;
        this.target = target;
        this.gravity = gravity;
    }

    public Vector getSource() {
        return source;
    }

    public void setSource(Vector source) {
        this.source = source;
    }

    public Vector getTarget() {
        return target;
    }

    public void setTarget(Vector target) {
        this.target = target;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}
