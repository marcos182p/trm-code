/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import trm.core.Stone;

/**
 * Painel de jogo sobre o qual serão
 * desenhados os dominós
 * @author rafanet
 */
public class BoardPanel extends JPanel{

    private Image background;
    private List<DominoView> dominos;
    private Color playerColor;
    private Color othersColor;
    public static final int BOARD_BORDER = 16;
    private DominosGrid grid;

    public BoardPanel(String backgroundBoard, int rows, int cols, Color playerColor, Color othersColor) {
        
        this.dominos = new ArrayList<DominoView>();
        this.playerColor = playerColor;
        this.othersColor = othersColor;
        setPreferredSize(new Dimension(rows * DominoView.SIZE + 2*BOARD_BORDER, cols * DominoView.SIZE + 2*BOARD_BORDER));
        background = ImageLoader.loadBackgroundImage(backgroundBoard);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        grid = new DominosGrid(rows, cols);
    }

    private void addStone(Stone s, int row, int col, Orientation orientation, boolean player) {
        Color c = playerColor;
        if(!player) {
            c = othersColor;
        }
        DominoView dv = new DominoView(s, row, col, orientation, c);
        dominos.add(dv);
        repaint();
    }

    private Orientation turnOrientation(Orientation orientation, StoneSide side) {
        if(side == StoneSide.UP) {
            return orientation;
        }else {
            return Orientation.clockwise(Orientation.clockwise(orientation));
        }
    }
    public void putStone(Stone s, GameSide gameSide, StoneSide stoneSide, boolean player) {
        grid.putRight();
        Point position = grid.currentPoint(gameSide);
        Orientation orientation= grid.currentOrientation(gameSide);
        addStone(s, position.y, position.x, orientation, player);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        Graphics g2 = g.create(BOARD_BORDER, BOARD_BORDER, this.getWidth() - BOARD_BORDER, this.getHeight() - BOARD_BORDER);
        for(Drawable d : dominos) {
            d.draw(g2);
        }
        //Teste para verificar onde se encontram as posicoes dos dominos
        /*for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid.getValueAt(i, j) > 0) {
                    g2.setColor(new Color(255,255,255,150));
                    g2.fillRect(j*DominoView.SIZE, i*DominoView.SIZE, DominoView.SIZE, DominoView.SIZE);
                }
            }
        }*/
    }
}
