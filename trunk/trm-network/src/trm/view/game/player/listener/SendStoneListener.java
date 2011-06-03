/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.player.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import trm.core.Stone;
import trm.view.game.board.BoardPanel;
import trm.view.game.player.PlayerPanel;
import trm.view.game.utils.GameSide;
import trm.view.game.utils.StoneSide;

/**
 * 
 * @author Rafael
 */
public class SendStoneListener implements ActionListener{

    private PlayerPanel panel;
    private BoardPanel board;
    private GameSide side;

    public SendStoneListener(PlayerPanel panel, BoardPanel board,GameSide side) {
        this.panel = panel;
        this.side = side;
        this.board = board;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        Stone stone = panel.getSelectedStone();
        
        if(stone != null) {
            if(acceptTransition(stone, side)) {
                panel.removePiece(stone);
                board.putStone(stone, side, StoneSide.UP, true);
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
