/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.game.board;

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
import trm.core.Stone;
import trm.view.game.utils.BGPanel;
import trm.view.utils.Drawable;

/**
 * Painel de jogo sobre o qual serão
 * desenhados os dominós
 * @author rafanet
 */
public class BoardPanel extends BGPanel{

    private List<DominoView> dominos;
    private Color playerColor;
    private Color othersColor;
    public static final int BOARD_BORDER = 16;
    private DominosGrid grid;
    private int rows, cols;

    public BoardPanel(String backgroundBoard, int rows, int cols, Color playerColor, Color othersColor) {
        super(backgroundBoard);
        this.rows = rows;
        this.cols = cols;
        this.dominos = new ArrayList<DominoView>();
        this.playerColor = playerColor;
        this.othersColor = othersColor;

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
}
