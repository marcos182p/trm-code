/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import trm.core.Stone;

/**
 *
 * @author rafanet
 */
public class PlayerPanel extends JPanel{
    private static final int NUM_PIECES = 7;
    private Map<Stone, JButton> dominos;
    private JLabel selectedPiece;
    private Color playerColor;

    public PlayerPanel(Color playerColor) {
        JLabel label = new JLabel();
        dominos = new HashMap<Stone, JButton>();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.playerColor = playerColor;
    }
    
    public void addPiece(Stone s) {
        JButton b = new JButton();
        b.setIcon(getImageIcon(s, 0));
        b.setSelectedIcon(getImageIcon(s, 50));
        dominos.put(s, b);
        b.getText();
        add(b);
    }

    private ImageIcon getImageIcon(Stone s, int alpha){
        DominoView dv = new DominoView(s, 0, 0, Orientation.SOUTH, Color.RED);
        dv.setColorOpacity(alpha);
        Image dominoImage = dv.getFullImage();
        return new ImageIcon(dominoImage);
    }

    public void setPiece(JButton button) {
        for(Stone s : dominos.keySet()) {
            if(dominos.get(s).equals(button)) {
                dominos.get(s).setIcon(dominos.get(s).getSelectedIcon());
            }else {
                dominos.get(s).setIcon(getImageIcon(s, 0));
            }
        }
    }

    public void removePiece(Stone s) {
        JButton b = dominos.get(s);
        remove(b);
        dominos.remove(s);
    }
    
    public void setPieces(Stone... stones) {
        if(!dominos.isEmpty()) {
            for(JButton b : dominos.values()) {
                remove(b);
            }
            dominos.clear();
        }
        for(Stone s : stones) {
            addPiece(s);
        }
    }
}
