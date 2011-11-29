package trm.model;

public class Problem {

    private Vector2D source;
    private Vector2D target;
    private float gravity;

    public Problem(Vector2D source, Vector2D target, float gravity) {
        this.source = source;
        this.target = target;
        this.gravity = gravity;
    }

    public Vector2D getSource() {
        return source;
    }

    public void setSource(Vector2D source) {
        this.source = source;
    }

    public Vector2D getTarget() {
        return target;
    }

    public void setTarget(Vector2D target) {
        this.target = target;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
}
