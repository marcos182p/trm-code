/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.login.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.client.ClientTask;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.view.game.login.LoginScreen;
import trm.view.game.main.GameScreen;

/**
 *
 * @author Rafael
 */
public class LoginListener implements ActionListener{
    private LoginScreen screen;
    
    public LoginListener(LoginScreen screen) {
        this.screen = screen;
    }
    
    public void actionPerformed(ActionEvent e) {
        try {
            
            if(screen.getNickname() != null && screen.getServer() != null) {
                
                ClientTask task = screen.createTask();
                task.subscribe(RequestType.LOGIN, screen);
                new Thread(task).start();
                
                task.sendRequest(new RequestClient(RequestType.LOGIN, screen.getNickname(), null, null, null));
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
