/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.player.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.client.ClientTask;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;

/**
 *
 * @author rafanet
 */
public class StartGameListener implements ActionListener{
    private ClientTask task;

    public StartGameListener(ClientTask task) {
        this.task = task;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            task.sendRequest(new RequestClient(RequestType.START_GAME));
        } catch (IOException ex) {
            Logger.getLogger(StartGameListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
