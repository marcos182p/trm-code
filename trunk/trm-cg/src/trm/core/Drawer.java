/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.core;

import java.awt.Color;
import javax.media.opengl.GL2;

/**
 *
 * @author mpjms
 */
public class Drawer {

    public static void drawPolygon(Polygon pol, GL2 gl, Color cubeColor, boolean drawLines) {
        Vector n = pol.getNormalVector();
        gl.glColor3f(cubeColor.getRed()/255.0f,
                cubeColor.getGreen()/255.0f,
                cubeColor.getBlue()/255.0f);
        gl.glNormal3f(n.getX(), n.getY(), n.getZ());
        gl.glBegin(GL2.GL_POLYGON);
        for(Vector point : pol.getPoints()) {
            gl.glVertex3f(point.getX(), point.getY(), point.getZ());
        }
        gl.glEnd();
        /*
        if(drawLines) {
            gl.glBegin(GL2.GL_LINE_LOOP);
            gl.glColor3f(0,0,0);
            n = Vector.getScalar(n, 0.005f);
            for(Vector point : pol.getPoints()) {
                gl.glVertex3f(point.getX() + n.getX(), point.getY() + n.getY(), point.getZ() + n.getZ());
            }
            gl.glEnd();
        }*/
    }

    public static void drawPolygon(Polygon pol, GL2 gl){
        drawPolygon(pol, gl, Color.WHITE, true);
    }

    public static void drawSolid(Solid solid, GL2 gl, Color cubeColor, boolean drawLines) {
        for(Polygon pol : solid.getPolygons()) {
            drawPolygon(pol, gl, cubeColor, drawLines);
        }
    }

    public static void drawSolid(Solid solid, GL2 gl){
        for(Polygon pol : solid.getPolygons()) {
            drawPolygon(pol, gl);
        }
    }

    public static void drawSolidAt(Solid solid, GL2 gl, Color cubeColor, boolean drawLines, Vector position) {
        gl.glPushMatrix();
        gl.glTranslatef(position.getX(), position.getY(), position.getZ());
        drawSolid(solid, gl, cubeColor, drawLines);
        gl.glPopMatrix();
    }

    public static void drawSolidAt(Solid solid, GL2 gl, Vector position) {
        gl.glPushMatrix();
        gl.glTranslatef(position.getX(), position.getY(), position.getZ());
        drawSolid(solid, gl);
        gl.glPopMatrix();
    }
}
