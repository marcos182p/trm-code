/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trm.core.Stone;
import trm.view.PlayerPanel;
import trm.view.GameSide;

/**
 * 
 * @author Rafael
 */
public class SendStoneListener implements ActionListener{

    private PlayerPanel panel;
    private GameSide side;

    public SendStoneListener(PlayerPanel panel, GameSide side) {
        this.panel = panel;
        this.side = side;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Stone stone = panel.getSelectedStone();
        
        if(stone != null) {
            if(acceptTransition(stone, side)) {
                panel.removePiece(stone);
            }
        }
    }

    /*TODO enviar mensagem para domínio de que a peça pra determinado lado
     foi mandada e retornar true caso seja bem sucessido ou false caso contrario*/
    private boolean acceptTransition(Stone stone, GameSide side) {
        switch(side) {
            case LEFT:
                break;
            case RIGHT:
                break;
        }
        return true;
    }
}
