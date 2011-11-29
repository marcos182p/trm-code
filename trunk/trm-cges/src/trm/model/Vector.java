/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.model;

/**
 *
 * @author ttmo
 */
public class Vector {

    private float X;
    private float Y;

    public Vector(float x, float y) {
        this.X = x;
        this.Y = y;
    }

    public float getX() {
        return X;
    }

    public void setX(float X) {
        this.X = X;
    }

    public float getY() {
        return Y;
    }

    public void setY(float Y) {
        this.Y = Y;
    }

    public Vector sum(Vector v) {
        return new Vector(X + v.getX(), Y + v.getY());
    }

    public Vector scalar(float scalar) {
        return new Vector(scalar * X, scalar * Y);
    }

    public float getModulo() {
        return (float) Math.sqrt(X * X + Y * Y);
    }

    public Vector getNormalVetor() {
        return scalar(1 / getModulo());
    }
}
