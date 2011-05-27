/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Rafael
 */
public class DrawPanel extends JPanel{
    
    private List<Drawable> drawables;

    public DrawPanel() {
           drawables = new ArrayList<Drawable>();
           this.setPreferredSize(new Dimension(320,320));
    }

    public void addDrawable(Drawable d) {
        drawables.add(d);
    }
    public void removeDrawable(Drawable d) {
        drawables.remove(d);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Drawable d : drawables) {
            d.draw(g);
        }
    }
}
