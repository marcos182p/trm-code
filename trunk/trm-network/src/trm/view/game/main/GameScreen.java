/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.main;

import trm.view.game.player.PlayerPanel;
import trm.view.game.utils.BGPanel;
import trm.view.game.board.BoardPanel;
import trm.view.game.chat.ChatPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.UIManager;
import trm.core.SquareNumber;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.view.game.main.listener.GameScreenListener;
import trm.view.game.utils.ResourceWindow;

/**
 *
 * @author Rafael
 */
public class GameScreen extends JFrame{

    private BoardPanel board;
    private ChatPanel chatPanel;
    private PlayerPanel playerPanel;
    private BGPanel content;

    public GameScreen() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        ClientTask task = new ClientTask("Rafael", new Socket("localhost", 8080));

        addWindowListener(new GameScreenListener(task));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String bg = ResourceWindow.getResourceName(ResourceWindow.BG_IMAGE);
        String panel = ResourceWindow.getResourceName(ResourceWindow.PANEL_IMAGE);

        setSize(800,800);
        Color playerColor = Color.ORANGE ;
        Color othersColor = Color.BLACK;

        content = new BGPanel(bg);
        board = new BoardPanel(panel, 14, 14, playerColor, othersColor);
        chatPanel = new ChatPanel(panel, task.getSender());
        playerPanel = new PlayerPanel(panel, board, playerColor);

        task.subscribe(RequestType.POST_MESSAGE, chatPanel);
        
        new Thread(task).start();

        setup();
        setResizable(false);
    }

    private void setup() {

        content.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.6;
        c.weighty = 0.6;
        content.add(board, c);
        c.weightx = 0.2;
        c.weighty = 0.2;
        c.insets = new Insets(5,50,5,50);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;
        content.add(playerPanel, c);
        c.gridy = 2;
        content.add(chatPanel,c);
        getContentPane().add(content);
    }

    public void addPlayerPiece(
            SquareNumber left, SquareNumber right) {
        
            Stone s = new Stone(left, right);
            playerPanel.addPiece(s);
        
    }

    public void open() {
        setVisible(true);
    }
}
