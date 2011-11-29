package trm.core.data;

import java.util.ArrayList;
import java.util.List;

public class Solid {

    private List<Polygon> polygons;

    public Solid() {
        polygons = new ArrayList<Polygon>();
    }

    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    public void addPolygon(Polygon polygon) {
        this.polygons.add(polygon);
    }

    public void removePolygon(Polygon polygon) {
        this.polygons.remove(polygon);
    }

    public List<Polygon> getPolygons() {
        return polygons;
    }
}
