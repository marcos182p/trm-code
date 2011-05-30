/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trm.core.Stone;
import trm.view.PlayerPanel;

/**
 *
 * @author Rafael
 */
public class DominoButtonListener implements ActionListener{

    private Stone stone;
    private PlayerPanel panel;

    public DominoButtonListener(PlayerPanel panel,Stone stone) {
        this.stone = stone;
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        panel.selectPiece(stone);
    }
}
