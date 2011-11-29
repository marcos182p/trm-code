package trm.missile.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;

public abstract class Drawable {

    public void draw(Graphics2D g, int w, int h, JComponent head) {
        Image im = head.createImage(w, h);
        if (im == null) {
            System.out.println("error on drawing");
            System.exit(1);
        }
        Graphics buffer = im.getGraphics();
        drawIn((Graphics2D) buffer);
        g.drawImage(im, 0, 0, null);
    }

    protected abstract void drawIn(Graphics2D g);
}
