package trm.core.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mpjms
 */
public class Polygon {
    List<Vector3D> points;

    public Polygon() {
        points = new ArrayList<Vector3D>();
    }

    public void setPoints(List<Vector3D> points) {
        this.points =points;
    }
    public void addPoint(Vector3D point) {
        this.points.add(point);
    }
    public void removePoint(Vector3D point) {
        this.points.remove(point);
    }
    public Vector3D getNormalVector() {
        if(points.size() < 3) {
            return new Vector3D(0,0,0);
        }
        Vector3D p1 = points.get(0);
        Vector3D p2 = points.get(1);
        Vector3D p3 = points.get(2);

        Vector3D p12 = p2.getSub(p1);
        Vector3D p13 = p3.getSub(p1);

        return Vector3D.outerProduct(p12, p13);
    }

    public List<Vector3D> getPoints() {
        return points;
    }


}
