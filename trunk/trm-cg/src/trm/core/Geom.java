/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core;

/**
 *
 * @author mpjms
 */
public class Geom {

    public static Polygon makeQuad(Vector p1, Vector p2, Vector p3, Vector p4) {
        Polygon pol = new Polygon();
        pol.addPoint(p1);
        pol.addPoint(p2);
        pol.addPoint(p3);
        pol.addPoint(p4);
        return pol;
    }

    public static Solid makePrism(float w, float h, float l) {
        Solid s = new Solid();
        Vector p1 = new Vector(0,0,0);
        Vector p2 = new Vector(w,0,0);
        Vector p3 = new Vector(w,0,l);
        Vector p4 = new Vector(0,0,l);
        Vector p5 = new Vector(0,h,0);
        Vector p6 = new Vector(w,h,0);
        Vector p7 = new Vector(w,h,l);
        Vector p8 = new Vector(0,h,l);


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
