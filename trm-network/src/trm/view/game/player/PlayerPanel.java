/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trm.view.game.player;

import trm.net.model.protocol.ResponseServer;
import trm.view.game.board.DominoView;
import trm.view.game.utils.GameSide;
import trm.view.game.utils.Orientation;
import trm.view.game.board.BoardPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import trm.core.Movement;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.net.client.Listener;
import trm.net.model.protocol.ResponseType;
import trm.view.game.player.listener.DominoButtonListener;
import trm.view.game.player.listener.SendStoneListener;
import trm.view.game.player.listener.StartGameListener;
import trm.view.game.utils.BGPanel;
import trm.view.game.utils.ButtonPanel;

/**
 *
 * @author rafanet
 */
public class PlayerPanel extends BGPanel implements Listener {

    private Map<Stone, JButton> dominos;
    private Color playerColor;
    private Stone selectedStone;
    private static final int selectedAlpha = 70;
    private ButtonPanel buttons;
    private String playerNickname;

    public PlayerPanel(String background, ClientTask task, String playerNickname, BoardPanel board, Color playerColor) {
        super(background);
        this.dominos = new HashMap<Stone, JButton>();
        this.playerColor = playerColor;
        this.selectedStone = null;
        this.playerNickname = playerNickname;

        buttons = new ButtonPanel(background, ButtonPanel.VERTICAL, "<<", "pass", ">>", "start");

        buttons.getButton("<<").addActionListener(new SendStoneListener(this, task, board, GameSide.LEFT));
        buttons.getButton(">>").addActionListener(new SendStoneListener(this, task, board, GameSide.RIGHT));
        buttons.getButton("pass").addActionListener(new SendStoneListener(this, task, board, null));
        buttons.getButton("start").addActionListener(new StartGameListener(task));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setup();
    }

    private void setup() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        add(buttons);
    }

    public void addPiece(Stone s) {
        JButton b = new JButton();
        b.setIcon(getImageIcon(s, 0));
        b.setRolloverIcon(getImageIcon(s, selectedAlpha));
        dominos.put(s, b);
        b.getText();
        b.addActionListener(new DominoButtonListener(this, s));
        add(b);
        repaint();

    }

    public boolean containsPiece(Stone s) {
        return dominos.containsKey(s);
    }

    private ImageIcon getImageIcon(Stone s, int alpha) {
        DominoView dv = new DominoView(s, 0, 0, Orientation.SOUTH, playerColor, true);
        dv.setColorOpacity(alpha);
        Image dominoImage = dv.getFullImage();
        return new ImageIcon(dominoImage);
    }

    public void selectPiece(Stone stone) {
        if (dominos.keySet().contains(stone)) {
            selectedStone = stone;
        }

        for (Stone s : dominos.keySet()) {
            dominos.get(s).setIcon(getImageIcon(s, 0));
        }
        dominos.get(stone).setIcon(getImageIcon(stone, selectedAlpha));
    }

    public Stone getSelectedStone() {
        return this.selectedStone;
    }

    public void removePiece(Stone s) {
        JButton b = dominos.get(s);
        remove(b);
        dominos.remove(s);
        selectedStone = null;
        this.repaint();
    }

    public void setPieces(List<Stone> stones) {
        if (!dominos.isEmpty()) {
            for (JButton b : dominos.values()) {
                remove(b);
            }
            dominos.clear();
        }
        for (Stone s : stones) {
            addPiece(s);
        }
        repaint();
    }

    @Override
    public void update(ResponseServer response) {
        if (response.getResponseType() == ResponseType.ERRO) {
            JOptionPane.showMessageDialog(null, response.erroMessage);
            return;
        } else {

            switch (response.getRequestType()) {
                case GET_HAND:
                    List<Stone> stones = response.stones;
                    System.out.println("stones list: " + stones);
                    System.out.println("stone list size" + stones.size());
                    System.out.println("request" + response.getRequestType());
                    setPieces(stones);
                    break;
                case PUT_STONE:
                    if (response.movement.action != Movement.Action.PASS) {
                        if (response.player.getNickName().equals(playerNickname)) {
                            Stone stone = response.movement.stone;
                            Stone inverted = new Stone(stone.getSquareRight(), stone.getSquareLeft());
                            if (containsPiece(stone)) {
                                removePiece(stone);
                            } else if (containsPiece(inverted)) {
                                removePiece(inverted);
                            }
                        }
                    }
            }
        }
    }
}
