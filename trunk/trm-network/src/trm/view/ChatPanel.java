/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import trm.view.listeners.ChatListener;

/**
 *
 * @author rafanet
 */
public class ChatPanel extends JPanel {

    private JLabel label;
    private JTextField chat;
    private JTextArea chatArea;
    private String background;

    public ChatPanel(String background) {
        this.background = background;
        this.label = new JLabel("Chat: ");
        this.chat = new JTextField(45);
            chat.addActionListener(new ChatListener(this, chat));
        this.chatArea = new JTextArea();
        JScrollPane pane = new JScrollPane(chatArea);

        chatArea.setAutoscrolls(true);
        chatArea.setRows(3);

        chatArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        add(label, c);
        c.gridx = 1;
        c.gridy = 0;
        add(chat, c);
        c.gridx = 1;
        c.gridy = 1;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(pane, c);


        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setText(String text) {
        chatArea.append(text);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(BackgroundImageLoader.loadBackgroundImage(background), 0, 0, getWidth(), getHeight(), null);
    }
}
