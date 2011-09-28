/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mpjms
 */
public class Polygon {
    List<Vector> points;

    public Polygon() {
        points = new ArrayList<Vector>();
    }

    public void setPoints(List<Vector> points) {
        this.points =points;
    }
    public void addPoint(Vector point) {
        this.points.add(point);
    }
    public void removePoint(Vector point) {
        this.points.remove(point);
    }
    public Vector getNormalVector() {
        if(points.size() < 3) {
            return new Vector(0,0,0);
        }
        Vector p1 = points.get(0);
        Vector p2 = points.get(1);
        Vector p3 = points.get(2);

        Vector p12 = Vector.getSub(p2, p1);
        Vector p13 = Vector.getSub(p3, p1);

        return Vector.outerProduct(p12, p13);
    }

    public List<Vector> getPoints() {
        return points;
    }
}
