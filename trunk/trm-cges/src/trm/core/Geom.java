package trm.core;

import trm.core.data.Vector3D;
import trm.core.data.Solid;
import trm.core.data.Polygon;

/**
 *
 * @author mpjms
 */
public class Geom {

    public static Polygon makeQuad(Vector3D p1, Vector3D p2, Vector3D p3, Vector3D p4) {
        Polygon pol = new Polygon();
        pol.addPoint(p1);
        pol.addPoint(p2);
        pol.addPoint(p3);
        pol.addPoint(p4);
        return pol;
    }



    public static Solid makePrism(float w, float h, float l) {
        Solid s = new Solid();
        Vector3D p1 = new Vector3D(0,0,0);
        Vector3D p2 = new Vector3D(w,0,0);
        Vector3D p3 = new Vector3D(w,0,l);
        Vector3D p4 = new Vector3D(0,0,l);
        Vector3D p5 = new Vector3D(0,h,0);
        Vector3D p6 = new Vector3D(w,h,0);
        Vector3D p7 = new Vector3D(w,h,l);
        Vector3D p8 = new Vector3D(0,h,l);


        s.addPolygon(makeQuad(p1,p2,p3,p4));
        s.addPolygon(makeQuad(p1,p5,p6,p2));
        s.addPolygon(makeQuad(p1,p4,p8,p5));
        s.addPolygon(makeQuad(p7,p6,p5,p8));
        s.addPolygon(makeQuad(p7,p8,p4,p3));
        s.addPolygon(makeQuad(p7,p3,p2,p6));

        return s;
    }

    public static Solid makeCube(float size) {
        return makePrism(size, size, size);
    }
}
