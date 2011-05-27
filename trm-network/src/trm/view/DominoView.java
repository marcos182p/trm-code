/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Graphics;
import java.awt.Image;
import trm.core.SquareNumber;
import trm.core.Stone;

/**
 *
 * @author marcos182p
 */
public class DominoView implements Drawable{

    private static final int SIZE = 32;//peca com 32 pixels
    private Stone stone;
    private Image left;
    private Image right;
    private int row;
    private int col;
    
    public DominoView(Stone s, int row, int col) {
        this.stone = s;
        this.row = row;
        this.col = col;
        this.left = DominoImageLoader.loadDominoImage(s.getSquareLeft());
        this.right = DominoImageLoader.loadDominoImage(s.getSquareRight());
    }

    public void draw(Graphics g) {
        
    }
}
