/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.chat;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import sun.font.Font2D;
import trm.net.client.Listener;
import trm.net.model.Sender;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.ResponseServer;
import trm.view.game.chat.listener.ChatListener;
import trm.view.game.utils.BGPanel;

/**
 *
 * @author rafanet
 */
public class ChatPanel extends BGPanel implements  Listener{

    private JLabel label;
    private JTextField chat;
    private JTextArea chatArea;

    public ChatPanel(String background, Sender<RequestClient> sender) {
        super(background);
        
        this.label = new JLabel("Chat: ");
        this.chat = new JTextField(45);
            chat.addActionListener(new ChatListener(sender, chat));
        this.chatArea = new JTextArea();
        this.chatArea.setEditable(false);
        this.chatArea.setWrapStyleWord(true);
        
        Font f = this.chatArea.getFont();
        Font f2 = new Font(f.getName(), f.getStyle(), f.getSize()-2);
        
        this.chatArea.setFont(f2);

        setup();
    }

    private void setup() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
    }

    public void appendMessage(String text) {
        chatArea.append(text + System.getProperty("line.separator"));
        chatArea.setCaretPosition( chatArea.getText().length() );
    }

    @Override
    public void update(ResponseServer response) {
        appendMessage(response.player.getNickName() + " >> " + response.chatMessage);
    }

}
