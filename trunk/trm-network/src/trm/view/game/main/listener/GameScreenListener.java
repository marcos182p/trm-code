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
import trm.sound.game.MidiPlayer;

/**
 *
 * @author rafanet
 */
public class GameScreenListener implements WindowListener{

    private ClientTask task;
    private String nickname;
    private String room;
    
    public GameScreenListener(ClientTask taskToClose,String nickname, String room) {
        this.task = taskToClose;
        this.nickname = nickname;
        this.room = room;
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        try {
            task.sendRequest(new RequestClient(RequestType.LOGIN, nickname, null, null, null));
            task.sendRequest(new RequestClient(RequestType.PUT_ROOM, null, room, null, null));
            task.sendRequest(new RequestClient(RequestType.ENTER_ROOM, nickname, room, null, null));
            task.sendRequest(new RequestClient(RequestType.GET_PLAYERS, null, room, null, null));
        }catch(Exception e) {
            
        }
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        try {
            MidiPlayer.close();
            task.sendRequest(new RequestClient(RequestType.EXIT_ROOM, nickname, room, null, null));
            
        } catch (Exception ex) {
            Logger.getLogger(GameScreenListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        try {
            task.sendRequest(new RequestClient(RequestType.CLOSE_CONNECTION));
            task.close();
        } catch (Exception ex) {
            Logger.getLogger(GameScreenListener.class.getName()).log(Level.SEVERE, null, ex);
        }
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
