package trm.core;

import com.sun.opengl.util.Animator;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

/**
 * @author Brian Paul
 * Converted to Java by Ron Cemer and Sven Goethel,
 * ported to JOGL2 by Michael Bien.
 *
 * This code is based on Brian Paul's version 1.2 1999/10/21
 */
public class TrmCg implements GLEventListener {

    public static void main(String[] args) {

        Frame frame = new Frame("Simple JOGL Application");

        // use GL2 profile since we only use the old OpenGL 2.x fixed function pipeline
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));

        // try to enable 2x anti aliasing - should be supported on most hardware
//        capabilities.setNumSamples(2);
//        capabilities.setSampleBuffers(true);

        GLCanvas canvas = new GLCanvas(capabilities);

        canvas.addGLEventListener(new TrmCg());
        frame.add(canvas);
        frame.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent ke) {
                System.out.println("keyTyped");
            }

            public void keyPressed(KeyEvent ke) {
                System.out.println("keyPressed");
            }

            public void keyReleased(KeyEvent ke) {
                System.out.println("keyReleased");
            }
        });
        // use JOGL's Animator utility for rendering
        final Animator animator = new Animator(canvas);

        // stop the Animator when we receive a window closing event
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        // Center frame, set its size and start rendering
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    // GLEventListener methods
    public void init(GLAutoDrawable drawable) {
        
        final float light_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};
        
        final float light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        
        final float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        
        final float light_position[] = {0.0f, 1.1f, -6.0f, 1.0f};

        
        final float mat_ambient[] = {0.7f, 0.7f, 0.7f, 1.0f};
        
        final float mat_diffuse[] = {0.8f, 0.8f, 0.8f, 1.0f};
       
        final float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        
        final float high_shininess[] = {100.0f};
        // Use debug pipeline, all OpenGL error codes will be automatically
        // converted to GLExceptions as soon as they appear
        drawable.setGL(new DebugGL2(drawable.getGL().getGL2()));

        GL2 gl = drawable.getGL().getGL2();
        System.out.println("INIT GL2 IS: " + gl.getClass().getName());
        gl.glEnable(gl.GL_DEPTH_TEST);

        // Enable VSync - this limits the rendering FPS to screen refresh rate
        gl.setSwapInterval(1);

        gl.glEnable(gl.GL_LIGHT0);
        gl.glEnable(gl.GL_NORMALIZE);
        gl.glEnable(gl.GL_LIGHTING);

        gl.glLightfv(gl.GL_LIGHT0, gl.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(gl.GL_LIGHT0, gl.GL_POSITION, light_position, 0);

        gl.glMaterialfv(gl.GL_FRONT, gl.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialfv(gl.GL_FRONT, gl.GL_SHININESS, high_shininess, 0);

        // Setup the drawing area and shading mode
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL2.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // avoid a divide by zero error!
        if (height <= 0) {
            height = 1;
        }

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glClearColor(1, 1, 1, 1);

        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {

        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        // Clear the drawing area
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
        glu.gluLookAt(0, 3, 2, 0, 3, -6, 0, 1, 0);
        // Move the "drawing cursor" around
        gl.glPushMatrix();
        Solid cube = Geom.makeCube(1);

        for (int i = -5; i < 5; i++) {
            for (int j = 0; j > -10; j--) {
                Drawer.drawSolidAt(cube, gl, new Vector(i, 0, j));
            }
        }

        for (int i = -5; i < 5; i++) {
            for (int j = 0; j > -10; j--) {
                Drawer.drawSolidAt(cube, gl, new Vector(i, 5, j));
            }
        }

        gl.glPopMatrix();

    }

    public void dispose(GLAutoDrawable arg0) {
    }
}
