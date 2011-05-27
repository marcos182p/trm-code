/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import trm.core.SquareNumber;
import trm.core.Stone;

/**
 *
 * @author marcos182p
 */
public class DominoView implements Drawable{

    private static final int SIZE = 32;//peca com 32 pixels
    private static final double ROT_ANGLE = Math.PI/2;
    private Image left;
    private Image right;
    private AffineTransform afLeft;
    private AffineTransform afRight;
    
    public DominoView(Stone s, final int row, final int col, Orientation orientation) {
        this.left = DominoImageLoader.loadDominoImage(s.getSquareLeft());
        this.right = DominoImageLoader.loadDominoImage(s.getSquareRight());
        afLeft = new AffineTransform();
        afRight = new AffineTransform();

        int rowLeft = row;
        int colLeft = col;
        int rowRight = row;
        int colRight = col;
        double angleLeft = 0;
        double angleRight = 0;
        switch(orientation) {
            case NORTH:
                angleLeft = 2*ROT_ANGLE;
                rowLeft++;
                break;
            case EAST:
                angleLeft = -ROT_ANGLE;
                angleRight = ROT_ANGLE;
                colRight++;
                break;
            case SOUTH:
                angleRight = 2*ROT_ANGLE;
                rowRight++;
                break;
            case WEST:
                angleLeft = ROT_ANGLE;
                angleRight = -ROT_ANGLE;
                colLeft++;
                break;
        }
        
        afRight.translate(colRight * SIZE, rowRight * SIZE);
        afLeft.translate(colLeft * SIZE , rowLeft * SIZE );
        afLeft.rotate(angleLeft,SIZE/2, SIZE/2);
        afRight.rotate(angleRight, SIZE/2, SIZE/2);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(left, afLeft, null);
        g2d.drawImage(right, afRight, null);
    }
}
