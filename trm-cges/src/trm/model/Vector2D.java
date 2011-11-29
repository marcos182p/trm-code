package trm.model;

public class Vector2D {

    private double x, y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double module() {
        return Math.sqrt(x * x + y * y);
    }

    public double innerProduct(Vector2D v) {
        return v.x * x + v.y * y;
    }

    public Vector2D sum(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    public Vector2D sub(Vector2D v) {
        return new Vector2D(x - v.x, y - v.y);
    }

    public Vector2D scalar(double alpha) {
        return new Vector2D(x * alpha, y * alpha);
    }

    public Vector2D normalized() {
        return scalar(1.0 / module());
    }
}
