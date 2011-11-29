package trm.core.data;

/**
 *
 * @author mpjms
 */
public class Vector3D {

    private float x;
    private float y;
    private float z;

    public Vector3D(float x, float y, float z) {
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

        public float getModule() {
        return (float) Math.sqrt(innerProduct(this, this));
    }

    public Vector3D getNormalizedVector() {
        float module = getModule();
        Vector3D v = new Vector3D(x / module, y / module, z / module);
        return v;
    }

    public Vector3D getSum( Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    public Vector3D getSub( Vector3D v) {
        return new Vector3D(x - v.x, y - v.y, z - v.z);
    }

    public Vector3D getScalar(float alpha) {
        return new Vector3D(x * alpha, y * alpha, z * alpha);
    }

    public static float innerProduct(Vector3D v, Vector3D u) {
        return v.x * u.x + v.y * u.y + v.z * u.z;
    }

    public static Vector3D outerProduct(Vector3D v, Vector3D u) {
        Vector3D v2 = new Vector3D(
                v.y * u.z - v.z * u.y,
                v.z * u.x - v.x * u.z,
                v.x * u.y - v.y * u.x);
        return v2;
    }


}
