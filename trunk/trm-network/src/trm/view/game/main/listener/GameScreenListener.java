/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.main.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.client.ClientTask;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;

/**
 *
 * @author rafanet
 */
public class GameScreenListener implements WindowListener{

    private ClientTask task;

    public GameScreenListener(ClientTask taskToClose) {
        this.task = taskToClose;
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        try {
            task.sendRequest(new RequestClient(RequestType.LOGIN, "Marcos", null, null, null));
            String roomGame = "test";
            task.sendRequest(new RequestClient(RequestType.PUT_ROOM, null, roomGame, null, null));
            task.sendRequest(new RequestClient(RequestType.ENTER_ROOM, null, roomGame, null, null));
        }catch(Exception e) {
            
        }
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        try {
            System.out.println("closing");
            task.close();
        } catch (Exception ex) {
            Logger.getLogger(GameScreenListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

}
