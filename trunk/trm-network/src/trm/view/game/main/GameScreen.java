/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.model.protocol.ResponseServer;
import trm.view.game.player.PlayerPanel;
import trm.view.game.utils.BGPanel;
import trm.view.game.board.BoardPanel;
import trm.view.game.chat.ChatPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import trm.core.PlayerInf;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.net.client.Listener;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseType;
import trm.sound.game.MidiPlayer;
import trm.view.game.main.listener.GameScreenListener;
import trm.view.game.player.PlayerList;
import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author Rafael
 */
public class GameScreen extends JFrame implements Listener {

    private BoardPanel board;
    private ChatPanel chatPanel;
    private PlayerPanel playerPanel;
    private BGPanel content;
    private PlayerList playerList;
    private ClientTask task;
    private PlayerInf player;
    private PlayerInf winner;

    public GameScreen(ClientTask task, PlayerInf inf, String server, String roomName) throws Exception {
        MidiPlayer.close();
        MidiPlayer.play("spring.mid");
        this.player = inf;
        this.task = task;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String bg = ResourceWindow.getResourceName(ResourceWindow.BG_IMAGE);
        String panel = ResourceWindow.getResourceName(ResourceWindow.PANEL_IMAGE);

        setSize(800, 800);
        Color playerColor = Color.GREEN;
        Color othersColor = Color.BLACK;

        content = new BGPanel(bg);
        board = new BoardPanel(panel, task, player.getNickName(), 14, 14, playerColor, othersColor);
        chatPanel = new ChatPanel(panel, task.getSender());
        playerPanel = new PlayerPanel(panel, task, player.getNickName(), board, playerColor);
        playerList = new PlayerList(panel);

        task.subscribe(RequestType.PUT_MESSAGE, chatPanel);
        task.subscribe(RequestType.GET_PLAYERS, playerList);
        task.subscribe(RequestType.ENTER_ROOM, this);
        task.subscribe(RequestType.EXIT_ROOM, this);
        task.subscribe(RequestType.PUT_STONE, board);
        task.subscribe(RequestType.START_GAME, this);
        task.subscribe(RequestType.GET_HAND, playerPanel);
        task.subscribe(RequestType.PUT_STONE, playerPanel);
        task.subscribe(RequestType.END_GAME, this);
        task.subscribe(RequestType.GET_WINNER, this);

        addWindowListener(new GameScreenListener(task, player.getNickName(), roomName));
        setup();
        setResizable(false);
        repaint();
    }

    private void setup() {

        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        BGPanel boardPanel = new BGPanel(ResourceWindow.getResourceName(ResourceWindow.BG_IMAGE));
        boardPanel.add(board);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        content.add(boardPanel, c);
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.weightx = 0.01;
        c.weighty = 0.01;
        c.gridy = 1;
        content.add(playerPanel, c);

        BGPanel panel = new BGPanel(ResourceWindow.getResourceName(ResourceWindow.BG_IMAGE));
        panel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.6;
        c.weighty = 1;
        panel.add(chatPanel, c);
        c.fill = GridBagConstraints.BOTH;
        //c.insets = new Insets(0, 30, 0, 5);
        c.gridx = 0;
        c.weightx = 0.4;
        panel.add(playerList, c);

        //c.insets = new Insets(10, 10, 0, 10);
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 2;
        content.add(panel, c);
        getContentPane().add(content);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(800,800);

        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
    }

    public void addPlayerPiece(
            SquareNumber left, SquareNumber right) {

        Stone s = new Stone(left, right);
        playerPanel.addPiece(s);

    }

    public void open() {
        setVisible(true);

    }

    @Override
    public void update(ResponseServer response) {
        if (response.getResponseType() == ResponseType.ERRO) {
            JOptionPane.showMessageDialog(null, response.erroMessage);
            return;
        }
        try {
            switch (response.getRequestType()) {
                case ENTER_ROOM:
                    task.sendRequest(new RequestClient(RequestType.GET_PLAYERS, null, null, null, null));
                    break;
                case EXIT_ROOM:
                    chatPanel.appendMessage("System: " + response.player.getNickName() + " desconectado... ");
                    break;
                case START_GAME:
                    MidiPlayer.close();
                    MidiPlayer.play("battle1.mid");
                    task.sendRequest(new RequestClient(RequestType.GET_HAND));
                    break;
                case END_GAME:
                    winner = response.player;
                    task.sendRequest(new RequestClient(RequestType.GET_WINNER));
                    break;
                case GET_WINNER:
                    
                    System.out.println(response.player);
                    if(winner != null) {
                        if (winner.equals(player)) {
                            MidiPlayer.close();
                            MidiPlayer.play("fanfare5.mid");
                            JOptionPane.showMessageDialog(null, "Parabéns você venceu!!!!");
                        } else {
                            MidiPlayer.close();
                            MidiPlayer.play("gameover2.mid");
                            JOptionPane.showMessageDialog(null, "Talvez na próxima, O jogador " + winner.getNickName() + " venceu");
                        }
                        board.clear();
                        playerPanel.clear();
                    }
                    MidiPlayer.close();
                    MidiPlayer.play("spring.mid");
                    break;
            }


        } catch (IOException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
