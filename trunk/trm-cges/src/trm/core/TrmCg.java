package trm.core;

import trm.core.data.Vector3D;
import trm.core.data.Solid;
import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import trm.core.commands.MoveCameraCmd;
import trm.core.commands.MoveCameraHorizontallyCmd;
import trm.core.commands.MoveCameraVerticallyCmd;
import trm.core.commands.TurnCameraCmd;
import trm.gui.InfoPanel;
import trm.tools.TextureReader;
import trm.tools.TextureReader.Texture;

/**
 * /home/users/mpjms/Desktop/trm-cg/dist/lib/natives-linux-i586
 */
public class TrmCg implements GLEventListener {

    private  Camera camera;
    private Solid cube;
    private int ids[];
    private Texture[] textures;
    private GLU glu = new GLU();

    public TrmCg(Camera camera) {
        this.camera = camera;
        cube = Geom.makeCube(1);
    }

    public static void main(String[] args) {

        final Frame frame = new Frame("Travel");
        

        // use GL2 profile since we only use the old OpenGL 2.x fixed function pipeline
        GLCapabilities capabilities = new GLCapabilities(GLProfile.get(GLProfile.GL2));

        // try to enable 2x anti aliasing - should be supported on most hardware
//        capabilities.setNumSamples(2);
//        capabilities.setSampleBuffers(true);

        final Camera camera = new Camera(
                new Vector3D(-16, 7, -18),
                new Vector3D(-15, 7, -17),
                new Vector3D(0, 1, 0));

        GLCanvas canvas = new GLCanvas(capabilities);

        final Controller controller = new Controller();

        Command turnRightCommand = new TurnCameraCmd(camera, -(float) Math.PI / 90);
        Command turnLeftCommand = new TurnCameraCmd(camera, (float) Math.PI / 90);
        Command moveForwardCommand = new MoveCameraCmd(camera, 0.2f);
        Command moveBackCommand = new MoveCameraCmd(camera, -0.2f);
        Command moveUpCommand = new MoveCameraVerticallyCmd(camera, 0.2f);
        Command moveDownCommand = new MoveCameraVerticallyCmd(camera, -0.2f);
        Command moveRightCommand = new MoveCameraHorizontallyCmd(camera, 0.2f);
        Command moveLeftCommand = new MoveCameraHorizontallyCmd(camera, -0.2f);


        controller.addCommand(KeyEvent.VK_RIGHT, turnRightCommand);
        controller.addCommand(KeyEvent.VK_LEFT, turnLeftCommand);
        controller.addCommand(KeyEvent.VK_UP, moveUpCommand);
        controller.addCommand(KeyEvent.VK_DOWN, moveDownCommand);
        controller.addCommand(KeyEvent.VK_W, moveForwardCommand);
        controller.addCommand(KeyEvent.VK_S, moveBackCommand);
        controller.addCommand(KeyEvent.VK_D, moveRightCommand);
        controller.addCommand(KeyEvent.VK_A, moveLeftCommand);


        canvas.addGLEventListener(new TrmCg(camera));

        /*
        frame.add(canvas, BorderLayout.CENTER);
        
        InfoPanel panel = new InfoPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Problema"));
        frame.add(panel, BorderLayout.EAST);
        */

        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        InfoPanel panel = new InfoPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Problema"));

        //-------------------------------------------------
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        frame.add(canvas, gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        frame.add(panel, gbc);
        //-------------------------------------------------

        frame.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
                controller.keyPressed(ke.getKeyCode());
                Vector3D pos = camera.getPosition();
                Vector3D center = camera.getCenter();
                System.out.println("pos: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
                System.out.println("center: " + center.getX() + ", " + center.getY() + ", " + center.getZ());
            }

            public void keyReleased(KeyEvent ke) {
                controller.keyReleased(ke.getKeyCode());
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
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(new Runnable() {

            public void run() {
                while (frame.isVisible()) {
                    try {
                        controller.update();
                        Thread.sleep(20);
                    } catch (InterruptedException ie) {
                    }
                }
            }
        }).start();

        animator.start();
    }

    // GLEventListener methods
    public void init(GLAutoDrawable drawable) {

        final float light_ambient[] = {0.1f, 0.1f, 0.1f, 1.0f};

        final float light_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};

        final float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};

        final float light_position[] = {0.0f, 10f, 0.0f, 1.0f};


        final float mat_ambient[] = {0.7f, 0.7f, 0.7f, 1.0f};

        final float mat_diffuse[] = {0.8f, 0.8f, 0.8f, 1.0f};

        final float mat_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};

        final float high_shininess[] = {30.0f};
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

        gl.glEnable(GL2.GL_TEXTURE_2D);

        String[] titles = {
            "src/resources/parede.png",
            "src/resources/wood_texture.png",
            "src/resources/crate.png",
            "src/resources/porta.png",
            "src/resources/ball.png",
            "src/resources/feno.png",
            "src/resources/barndoor.png"
        };

        try {
            ids = new int[titles.length];
            gl.glGenTextures(titles.length, ids, 0);
            textures = new Texture[ids.length];
            for(int i = 0; i < ids.length; i++) {
               gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[i]);
               textures[i] = TextureReader.readTexture(titles[i]);
               makeRGBTexture(gl, glu, textures[i], GL2.GL_TEXTURE_2D, false);
               gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
               gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);

            }

            camera.setBoundingBox(-25, 0, -25, 50, 20, 30);
            camera.setCameraSize(5,5,5);
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }


    }

    private void makeRGBTexture(GL2 gl, GLU glu, Texture img, int target, boolean mipmapped) {
        if (mipmapped) {
            glu.gluBuild2DMipmaps(target, GL.GL_RGB8, img.getWidth(), img.getHeight(), GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        } else {
            gl.glTexImage2D(target, 0, GL.GL_RGB, img.getWidth(), img.getHeight(), 0, GL.GL_RGB, GL.GL_UNSIGNED_BYTE, img.getPixels());
        }
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

        glu.gluPerspective(45.0f, h, 1.0, 100.0);
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
        Vector3D pos = camera.getPosition();
        Vector3D center = camera.getCenter();
        Vector3D up = camera.getUp();

        //gl.glLoadIdentity();
        glu.gluLookAt(
                pos.getX(), pos.getY(), pos.getZ(),
                center.getX(), center.getY(), center.getZ(),
                up.getX(), up.getY(), up.getZ());
        //gl.glPopMatrix();
        // Move the "drawing cursor" aroun

        Solid cube = Geom.makeCube(1);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[2]);


        gl.glPushMatrix();

        gl.glTranslatef(0,20,-15);
        gl.glScalef(0.5f,20,0.5f);
        gl.glRotatef(90,1,0,0);
        Drawer.drawCilynderWithTexture(gl, glu, 1, 1, 1, ids[2]);
        gl.glTranslatef(20,0,0);
        Drawer.drawCilynderWithTexture(gl, glu, 1, 1, 1, ids[2]);
        gl.glTranslatef(20,0,0);
        Drawer.drawCilynderWithTexture(gl, glu, 1, 1, 1, ids[2]);
        gl.glPopMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[5]);

        gl.glPushMatrix();
        gl.glTranslatef(20,0,-2);
        gl.glScalef(5,2,5);

        Drawer.drawSphereWithTexture(gl, glu, ids[5]);
        gl.glTranslatef(-1,0.1f,0);
        Drawer.drawSphereWithTexture(gl, glu, ids[5]);
        gl.glTranslatef(0.5f,-0.5f,0);
        gl.glScalef(1.2f,2.2f,1.2f);
        Drawer.drawSphereWithTexture(gl, glu, ids[5]);
        gl.glPopMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[2]);

        double size = 3;
        for (int i = 0; i < 5; i++) {
                gl.glPushMatrix();
                gl.glScaled(size,size,size);
                gl.glTranslated(i, 0, 0);
                Drawer.drawSolidWithTexture(cube, gl, true);
                gl.glPopMatrix();
        }

        for (int i = 0; i < 3; i++) {
                gl.glPushMatrix();
                gl.glScaled(size,size,size);
                gl.glTranslated(i+1, 1, 0);
                Drawer.drawSolidWithTexture(cube, gl, true);
                gl.glPopMatrix();
        }

        for (int i = 0; i < 4; i++) {
                gl.glPushMatrix();
                gl.glScaled(size,size,size);
                gl.glTranslated(i, 0, -1);
                Drawer.drawSolidWithTexture(cube, gl, true);
                gl.glPopMatrix();
        }

        gl.glPushMatrix();
        gl.glScaled(size,size,size);
        gl.glTranslated(1, 2, 0);
        Drawer.drawSolidWithTexture(cube, gl, true);
        gl.glPopMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[0]);

        gl.glPushMatrix();
        gl.glTranslatef(-25, 0, -25);
        gl.glScalef(50, 20, 30);
        Drawer.drawRevertedSolidWithTexture(cube, gl);
        gl.glPopMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[6]);
        gl.glPushMatrix();
        gl.glTranslatef(10, 3, -15);
        gl.glScalef(10,5,1);
        Drawer.drawSolidWithTexture(cube, gl, false);
        gl.glTranslatef(-1,0,0);
        Drawer.drawSolidWithTexture(cube, gl, false);
        gl.glTranslatef(0,0,-10);
        gl.glScalef(0.1f,1,9.5f);
        Drawer.drawSolidWithTexture(cube, gl, false);

        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(20, 3, -15);
        gl.glScalef(5, 5, 1);
        Drawer.drawSolidWithTexture(cube, gl, true);
        gl.glPopMatrix();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, ids[3]);
        gl.glPushMatrix();
        gl.glTranslatef(-16,0,4.5f);
        gl.glScalef(9,15,1);
        Drawer.drawSolidWithTexture(cube, gl, true);
        gl.glPopMatrix();


    }

    public void dispose(GLAutoDrawable arg0) {
    }
}

