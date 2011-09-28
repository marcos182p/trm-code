/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core;

/**
 *
 * @author mpjms
 */
public class Vector {
    private float x, y, z;

    public Vector(float x, float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public static Vector getSum(Vector u, Vector v) {
        return new Vector(u.x + v.x, u.y + v.y,u.z + v.z);
    }

    public static Vector getSub(Vector u, Vector v) {
        return new Vector(u.x - v.x, u.y - v.y,u.z - v.z);
    }

    public static Vector getScalar(Vector v, float alpha) {
        return new Vector(v.x * alpha, v.y * alpha, v.z * alpha);
    }

    public static float innerProduct(Vector v, Vector u) {
        return v.x * u.x + v.y * u.y + v.z * u.z;
    }

    public static Vector outerProduct(Vector v, Vector u) {
        Vector v2 = new Vector(
                v.y * u.z - v.z * u.y,
                v.z * u.x - v.x * u.z,
                v.x * u.y - v.y * u.x);
        return v2;
    }
    public float getModule() {
        return (float)Math.sqrt(innerProduct(this, this));
    }
    public Vector getNormalizedVector() {
        float module = getModule();
        Vector v = new Vector(x/module, y/module, z/module);
        return v;
    }
}
