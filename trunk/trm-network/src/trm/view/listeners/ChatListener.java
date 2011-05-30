/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import trm.view.ChatPanel;

/**
 *
 * @author Rafael
 */
public class ChatListener implements ActionListener{
    
    private JTextField field;
    private ChatPanel panel;

    public ChatListener(ChatPanel panel, JTextField field) {
        this.field = field;
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        panel.setText(field.getText() + System.getProperty("line.separator"));
        field.setText("");
    }
}
