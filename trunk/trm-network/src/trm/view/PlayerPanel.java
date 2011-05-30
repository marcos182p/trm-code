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
import trm.view.listeners.DominoButtonListener;

/**
 *
 * @author rafanet
 */
public class PlayerPanel extends JPanel{
    private Map<Stone, JButton> dominos;
    private Color playerColor;
    private Stone selectedStone;
    private static final int selectedAlpha = 50;
    private JButton sendLeft;
    private JButton sendRight;

    public PlayerPanel(Color playerColor) {
        sendLeft = new JButton("<<");
        sendRight = new JButton(">>");
        dominos = new HashMap<Stone, JButton>();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.playerColor = playerColor;
        selectedStone = null;
        this.add(sendLeft);
        this.add(sendRight);
    }
    
    public void addPiece(Stone s) {
        JButton b = new JButton();
        b.setIcon(getImageIcon(s, 0));
        b.setRolloverIcon(getImageIcon(s, selectedAlpha));
        dominos.put(s, b);
        b.getText();
        b.addActionListener(new DominoButtonListener(this, s));
        add(b);
    }

    private ImageIcon getImageIcon(Stone s, int alpha){
        DominoView dv = new DominoView(s, 0, 0, Orientation.SOUTH, playerColor);
        dv.setColorOpacity(alpha);
        Image dominoImage = dv.getFullImage();
        return new ImageIcon(dominoImage);
    }

    public void selectPiece(Stone stone) {
       if(dominos.keySet().contains(stone)) {
        selectedStone = stone;
       }
       
       for(Stone s : dominos.keySet()) {
            dominos.get(s).setIcon(getImageIcon(s, 0));
       }
       dominos.get(stone).setIcon(getImageIcon(stone, selectedAlpha));
    }

    public Stone getSelectedStone() {
        return this.selectedStone;
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
