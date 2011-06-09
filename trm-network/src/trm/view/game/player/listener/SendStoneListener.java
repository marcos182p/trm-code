package trm.view.game.player.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.core.Movement;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.view.game.board.BoardPanel;
import trm.view.game.player.PlayerPanel;
import trm.view.game.utils.GameSide;

/**
 * @author TRM
 * @version 0.99
 */
public class SendStoneListener implements ActionListener {

    private PlayerPanel panel;
    private BoardPanel board;
    private GameSide side;
    private ClientTask task;

    public SendStoneListener(PlayerPanel panel, ClientTask task, BoardPanel board, GameSide side) {
        this.panel = panel;
        this.side = side;
        this.board = board;
        this.task = task;
    }

    @Override
    /**TODO também preciso receber o lado que a peça está na orientação normal ou invertida*/
    public void actionPerformed(ActionEvent event) {
        Stone stone = panel.getSelectedStone();

        Movement.Action action = null;

        if (side == null) {
            System.out.println("SendStoneListener: pass");
            action = Movement.Action.PASS;
        } else if (stone != null) {
            System.out.println("SendStoneListener: move to");
            switch (side) {
                case LEFT:
                    System.out.println("SendStoneListener: left");
                    action = Movement.Action.PUT_LEFT;
                    break;
                case RIGHT:
                    System.out.println("SendStoneListener: right");
                    action = Movement.Action.PUT_RIGHT;
                    break;
            }
        }
        Movement m = new Movement(stone, action);

        System.out.println("SendStoneListener: movment is : " + m);
        try {
            task.sendRequest(new RequestClient(RequestType.PUT_STONE, null, null, m, null));
            task.sendRequest(new RequestClient(RequestType.GET_PLAYERS));
        } catch (IOException ex) {
            Logger.getLogger(SendStoneListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
