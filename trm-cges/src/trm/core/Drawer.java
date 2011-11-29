package trm.core;

import com.sun.opengl.impl.glu.GLUquadricImpl;
import trm.core.data.Vector3D;
import trm.core.data.Solid;
import trm.core.data.Polygon;
import java.awt.Color;
import java.util.List;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Drawer {

    public static void drawPolygon(Polygon pol, GL2 gl, Color cubeColor, boolean drawLines) {
        Vector3D n = pol.getNormalVector();
        gl.glColor3f(cubeColor.getRed() / 255.0f,
                cubeColor.getGreen() / 255.0f,
                cubeColor.getBlue() / 255.0f);
        gl.glNormal3f(n.getX(), n.getY(), n.getZ());
        gl.glBegin(GL2.GL_POLYGON);
        for (Vector3D point : pol.getPoints()) {
            gl.glVertex3f(point.getX(), point.getY(), point.getZ());
        }
        gl.glEnd();
    }

    public static void drawQuad(GL2 gl, Vector3D p1, Vector3D p2, Vector3D p3, Vector3D p4) {
        gl.glBegin(GL2.GL_POLYGON);
        gl.glTexCoord2d(0.0, 0.0);
        gl.glVertex3f(p1.getX(), p1.getY(), p1.getZ());
        gl.glTexCoord2d(1.0, 0.0);
        gl.glVertex3f(p2.getX(), p2.getY(), p2.getZ());
        gl.glTexCoord2d(1.0, 1.0);
        gl.glVertex3f(p3.getX(), p3.getY(), p3.getZ());
        gl.glTexCoord2d(0.0, 1.0);
        gl.glVertex3f(p4.getX(), p4.getY(), p4.getZ());
        gl.glEnd();
    }

    public static void drawPolygonWithTexture(Polygon pol, GL2 gl, boolean drawLine) {
        Vector3D n = pol.getNormalVector();

        gl.glNormal3f(n.getX(), n.getY(), n.getZ());
        List<Vector3D> points = pol.getPoints();

        Vector3D coord = new Vector3D(0, 0, 0);
        Vector3D dir = new Vector3D(4 / points.size(), 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < points.size(); i++) {
            Vector3D point = points.get(i);

            gl.glTexCoord2f(coord.getX(), coord.getY());
            gl.glVertex3f(point.getX(), point.getY(), point.getZ());
            coord = coord.getSum(dir);

            if (i % (points.size() / 4) == 0) {
                dir = new Vector3D(-dir.getY(), dir.getX(), 0);

            }
        }
        gl.glEnd();
    }

    public static void drawRevertedPolygon(Polygon pol, GL2 gl, Color cubeColor, boolean drawLines) {
        Vector3D n = pol.getNormalVector().getScalar(-1);
        gl.glColor3f(cubeColor.getRed() / 255.0f,
                cubeColor.getGreen() / 255.0f,
                cubeColor.getBlue() / 255.0f);
        gl.glNormal3f(n.getX(), n.getY(), n.getZ());
        gl.glBegin(GL2.GL_POLYGON);
        for (Vector3D point : pol.getPoints()) {
            gl.glVertex3f(point.getX(), point.getY(), point.getZ());
        }
        gl.glEnd();
    }

    public static void drawSphereWithTexture(GL2 gl, GLU glu, int myShpereID) {
        GLUquadric sphere = new GLUquadricImpl(gl, true);
        glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);

        glu.gluQuadricTexture(sphere, true);
        glu.gluQuadricNormals(sphere, GLU.GLU_SMOOTH);
        gl.glNewList(myShpereID, GL2.GL_COMPILE);
        glu.gluSphere(sphere, 1, 20, 20);
        gl.glEndList();
        glu.gluDeleteQuadric(sphere);
        gl.glCallList(myShpereID);
        gl.glDeleteLists(myShpereID, 1);
    }

    public static void drawCilynderWithTexture(GL2 gl, GLU glu, double base, double top, double height, int myCylinderID) {
        GLUquadric sphere = new GLUquadricImpl(gl, true);
        glu.gluQuadricDrawStyle(sphere, GLU.GLU_FILL);

        glu.gluQuadricTexture(sphere, true);
        glu.gluQuadricNormals(sphere, GLU.GLU_SMOOTH);
        gl.glNewList(myCylinderID, GL2.GL_COMPILE);
        glu.gluCylinder(sphere, base, top, height, 20, 20);
        gl.glEndList();
        glu.gluDeleteQuadric(sphere);
        gl.glCallList(myCylinderID);
        gl.glDeleteLists(myCylinderID, 1);
    }

    public static void drawRevertedPolygonWithTexture(Polygon pol, GL2 gl, boolean drawLines) {
        Vector3D n = pol.getNormalVector().getScalar(-1);
        gl.glNormal3f(n.getX(), n.getY(), n.getZ());
        List<Vector3D> points = pol.getPoints();
        Vector3D coord = new Vector3D(0, 1, 0);
        Vector3D dir = new Vector3D(4 / points.size(), 0, 0);
        gl.glBegin(GL2.GL_POLYGON);
        for (int i = 0; i < points.size(); i++) {
            Vector3D point = points.get(i);
            gl.glTexCoord2f(coord.getX(), coord.getY());
            gl.glVertex3f(point.getX(), point.getY(), point.getZ());
            coord = coord.getSum(dir);
            if (i % (points.size() / 4) == 0) {
                dir = new Vector3D(-dir.getY(), dir.getX(), 0);

            }
        }
        gl.glEnd();
    }

    public static void drawPolygon(Polygon pol, GL2 gl) {
        drawPolygon(pol, gl, Color.WHITE, true);
    }

    public static void drawRevertedPolygon(Polygon pol, GL2 gl) {
        drawRevertedPolygon(pol, gl, Color.WHITE, true);
    }

    public static void drawSolid(Solid solid, GL2 gl, Color cubeColor, boolean drawLines) {
        for (Polygon pol : solid.getPolygons()) {
            drawPolygon(pol, gl, cubeColor, drawLines);
        }
    }

    public static void drawSolidWithTexture(Solid solid, GL2 gl, boolean drawLines) {
        for (Polygon pol : solid.getPolygons()) {
            drawPolygonWithTexture(pol, gl, drawLines);
        }
    }

    public static void drawRevertedSolid(Solid solid, GL2 gl, Color cubeColor, boolean drawLines) {
        for (Polygon pol : solid.getPolygons()) {
            drawRevertedPolygon(pol, gl, cubeColor, drawLines);
        }
    }

    public static void drawRevertedSolidWithTexture(Solid solid, GL2 gl, boolean drawLines) {
        for (Polygon pol : solid.getPolygons()) {
            drawRevertedPolygonWithTexture(pol, gl, drawLines);
        }
    }

    public static void drawSolid(Solid solid, GL2 gl) {
        for (Polygon pol : solid.getPolygons()) {
            drawPolygon(pol, gl);
        }
    }

    public static void drawRevertedSolid(Solid solid, GL2 gl) {
        for (Polygon pol : solid.getPolygons()) {
            drawRevertedPolygon(pol, gl);
        }
    }

    public static void drawRevertedSolidWithTexture(Solid solid, GL2 gl) {
        for (Polygon pol : solid.getPolygons()) {
            drawRevertedPolygonWithTexture(pol, gl, true);
        }
    }
}
