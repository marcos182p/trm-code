/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import trm.net.client.ClientTask;
import trm.net.client.Listener;
import trm.net.model.protocol.ResponseServer;
import trm.net.model.protocol.ResponseType;
import trm.sound.game.MidiPlayer;
import trm.view.game.login.listener.LoginListener;
import trm.view.game.main.GameScreen;
import trm.view.game.utils.BGPanel;
import trm.view.game.utils.FieldPanel;
import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author Rafael
 */
public class LoginScreen extends JFrame implements Listener {

    private BGPanel panel;
    private FieldPanel login;
    private FieldPanel server;
    private JButton button;
    private ClientTask task;

    public LoginScreen() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        String bg = ResourceWindow.getResourceName(ResourceWindow.BG_IMAGE);
        String panelImg = ResourceWindow.getResourceName(ResourceWindow.PANEL_IMAGE);

        this.panel = new BGPanel(bg);
        this.login = new FieldPanel(panelImg, "Nickname: ", 45, false, FieldPanel.BY_SIDE);
        this.server = new FieldPanel(panelImg, "Server: ", 45, false, FieldPanel.BY_SIDE);

        this.server.getField().addActionListener(new LoginListener(this));
        this.login.getField().addActionListener(new LoginListener(this));
        button = new JButton("Login");
        button.addActionListener(new LoginListener(this));
        setSize(800, 800);
        setup();

    }

    public ClientTask createTask() throws IOException {
        task = new ClientTask(getNickname() , new Socket(getServer(), 8080));
        return this.task;
    }

    public  String getNickname() {
        return login.getContent();
    }

    public   String
        getServer() {
        return server.getContent();
    }

    public void open() {
        setVisible(true);
        MidiPlayer.play("icecave.mid");
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;

        c.weighty = 1;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.SOUTH;

        panel.add(login, c);
        c.insets = new Insets(0, 15, 75, 15);
        c.weighty = 0.01;
        c.gridy = 1;
        panel.add(server, c);

        c.gridx = 1;
        panel.add(button, c);
        c.insets = new Insets(0, 0, 0, 0);
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        c.weightx = 1;
        c.weighty = 1;
        add(panel, c);
    }

    @Override
    public void update(ResponseServer response) {
        if (response.getResponseType() != ResponseType.ERRO) {
            try {
                new GameScreen(task, response.player, getServer(), "teste").open();
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(LoginListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro", response.erroMessage, JOptionPane.ERROR_MESSAGE);
        }
    }
}
