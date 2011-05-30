/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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

    private int rows;
    private int cols;
    private Image background;
    private List<Drawable> drawables;
    private Color playerColor;
    private Color othersColor;

    public BoardPanel(String backgroundBoard, int rows, int cols, Color playerColor, Color othersColor) {
        this.rows = rows;
        this.cols = cols;
        this.drawables = new ArrayList<Drawable>();
        this.playerColor = playerColor;
        this.othersColor = othersColor;
        setPreferredSize(new Dimension(rows * DominoView.SIZE, cols * DominoView.SIZE));
        background = BackgroundImageLoader.loadBackgroundImage(backgroundBoard);
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    }

    public void addStone(Stone s, int row, int col, Orientation orientation, boolean player) {
        Color c = playerColor;
        if(!player) {
            c = othersColor;
        }
        DominoView dv = new DominoView(s, row, col, orientation, c);
        drawables.add(dv);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        for(Drawable d : drawables) {
            d.draw(g);
        }
    }
}
