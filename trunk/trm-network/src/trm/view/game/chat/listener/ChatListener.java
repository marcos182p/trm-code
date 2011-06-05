/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.chat.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.view.game.chat.ChatPanel;

/**
 *
 * @author Rafael
 */
public class ChatListener implements ActionListener{
    
    private Sender<RequestClient> sender;
    private JTextField field;

    public ChatListener(Sender<RequestClient> sender, JTextField field) {
        this.field = field;
        this.sender = sender;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String message = field.getText();
            
            sender.send(new RequestClient(RequestType.PUT_MESSAGE, null, null, null, message));
            field.setText("");
        } catch (IOException ex) {
            Logger.getLogger(ChatListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
