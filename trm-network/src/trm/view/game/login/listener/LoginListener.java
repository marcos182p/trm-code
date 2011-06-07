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
    private ClientTask task;
    
    public LoginListener(LoginScreen screen, ClientTask task) {
        this.screen = screen;
        this.task = task;
    }
    
    public void actionPerformed(ActionEvent e) {
        try {
            if(screen.getNickname() != null && screen.getServer() != null) {
                
                task = new ClientTask(screen.getNickname(), new Socket(screen.getServer(), 8080));
                task.subscribe(RequestType.LOGIN, screen);
                task.sendRequest(new RequestClient(RequestType.LOGIN, screen.getNickname(), null, null, null));
                try {
                    new GameScreen(task, screen.getNickname(), screen.getServer(), "teste").open();
                } catch (Exception ex) {
                    Logger.getLogger(LoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                screen.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
