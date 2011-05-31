/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Rafael
 */
public class AllPanel extends JPanel{
    private String background;
    public AllPanel(String background) {
        setBackground(Color.BLACK);
        this.background = background;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BackgroundImageLoader.loadBackgroundImage(background), 0,0,getWidth(), getHeight(), null);
    }
}
