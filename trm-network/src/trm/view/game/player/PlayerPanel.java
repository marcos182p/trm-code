/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.player;

import trm.view.game.board.DominoView;
import trm.view.game.utils.GameSide;
import trm.view.game.utils.Orientation;
import trm.view.game.board.BoardPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.view.game.player.listener.DominoButtonListener;
import trm.view.game.player.listener.SendStoneListener;
import trm.view.game.utils.BGPanel;
import trm.view.game.utils.ButtonPanel;

/**
 *
 * @author rafanet
 */
public class PlayerPanel extends BGPanel{
    private Map<Stone, JButton> dominos;
    private Color playerColor;
    private Stone selectedStone;
    private static final int selectedAlpha = 70;
    private ButtonPanel buttons;

    public PlayerPanel(String background, ClientTask task, BoardPanel board, Color playerColor) {
        super(background);
        this.dominos = new HashMap<Stone, JButton>();
        this.playerColor = playerColor;
        this.selectedStone = null;

        buttons = new ButtonPanel(background, ButtonPanel.VERTICAL, "<<", "pass", ">>");

        buttons.getButton("<<").addActionListener(new SendStoneListener(this, task, board, GameSide.LEFT));
        buttons.getButton(">>").addActionListener(new SendStoneListener(this, task, board, GameSide.RIGHT));
        buttons.getButton("pass").addActionListener(new SendStoneListener(this, task, board, null));
        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setup();
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx =0;
        c.gridy =0;
        c.weightx = 0;
        c.weighty = 0;
        add(buttons);
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
        DominoView dv = new DominoView(s, 0, 0, Orientation.SOUTH, playerColor, true);
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
        selectedStone = null;
        this.repaint();
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
