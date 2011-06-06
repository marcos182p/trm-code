/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.board;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import trm.net.model.protocol.ResponseServer;
import trm.view.game.utils.GameSide;
import trm.view.game.utils.StoneSide;
import trm.view.game.utils.Orientation;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import trm.core.Movement;
import trm.core.Stone;
import trm.net.client.ClientTask;
import trm.net.client.Listener;
import trm.net.model.protocol.RequestClient;
import trm.net.model.protocol.RequestType;
import trm.net.model.protocol.ResponseType;
import trm.view.game.utils.BGPanel;
import trm.view.utils.Drawable;

/**
 * Painel de jogo sobre o qual serão
 * desenhados os dominós
 * @author rafanet
 */
public class BoardPanel extends BGPanel implements Listener{

    private List<DominoView> dominos;
    private Color playerColor;
    private Color othersColor;
    public static final int BOARD_BORDER = 16;
    private DominosGrid grid;
    private String playerNickname;
    private ClientTask task;

    public BoardPanel(String backgroundBoard, ClientTask task,  String playerNickname, int rows, int cols, Color playerColor, Color othersColor) {
        super(backgroundBoard);
        this.dominos = new ArrayList<DominoView>();
        this.playerColor = playerColor;
        this.othersColor = othersColor;
        this.playerNickname = playerNickname;
        this.task = task;
        
        setPreferredSize(
                new Dimension(
                rows * DominoView.SIZE + 2*BOARD_BORDER,
                cols * DominoView.SIZE + 2*BOARD_BORDER));

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        grid = new DominosGrid(rows, cols);
    }

    private void addStone(Stone s, int row, int col, Orientation orientation, StoneSide stoneSide, boolean player, boolean anchorRight) {
        Color c = playerColor;
        if(!player) {
            c = othersColor;
        }
        DominoView dv = new DominoView(s, row, col, orientation, c, anchorRight);
        if(stoneSide == StoneSide.DOWN) {
            dv.invert();
        }
        dominos.add(dv);
        repaint();
    }

    public void putStone(Stone s, GameSide gameSide, StoneSide stoneSide, boolean player) {
        
        switch(gameSide) {
            case RIGHT:
                grid.putRight();
                break;
            case LEFT:
                grid.putLeft();
                break;
        }
        Point position = grid.currentPoint(gameSide);
        Orientation orientation= grid.currentOrientation(gameSide);
        
        addStone(s, position.y, position.x, orientation, stoneSide, player, gameSide == GameSide.RIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Drawable d : dominos) {
            d.draw(g);
        }
    }

    @Override
    /*FIXME preciso que response.movement seja recebido*/
    public void update(ResponseServer response) {
        System.out.println("BoardPanel: here i'm listening");
        Movement m = response.movement;
        System.out.println("BoardPanel: player = " + response.player.getNickName());
        System.out.println("BoardPanel: movement = " + m);
        if(response.getResponseType() == ResponseType.ERRO) {
            JOptionPane.showMessageDialog(null, response.erroMessage);
            return;
        }
        if(m != null) {
            switch(m.action) {
                case PUT_LEFT:
                    putStone(
                            m.stone,
                            GameSide.LEFT,
                            StoneSide.UP,
                            response.player.getNickName().equals(playerNickname));
                    break;
                case PUT_RIGHT:
                    putStone(
                            m.stone,
                            GameSide.RIGHT,
                            StoneSide.UP,
                            response.player.getNickName().equals(playerNickname));
                    break;
                case PASS:
                    break;
            }
        }
        try {
            task.sendRequest(new RequestClient(RequestType.GET_PLAYERS));
        } catch (IOException ex) {
            Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        repaint();
    }
}
