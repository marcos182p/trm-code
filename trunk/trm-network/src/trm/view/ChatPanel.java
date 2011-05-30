/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author rafanet
 */
public class ChatPanel extends JPanel{
    private JLabel label;
    private JTextField chat;
    private JTextArea chatArea;

    public ChatPanel() {
        label = new JLabel("Chat: ");
        chat = new JTextField(45);
        chatArea = new JTextArea();
        add(label);
        add(chat);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
