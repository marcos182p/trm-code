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
    private Stone stone;
    private Image left;
    private Image right;
    private AffineTransform afLeft;
    private AffineTransform afRight;
    
    public DominoView(Stone s, final int row, final int col, Orientation orientation) {
        this.stone = s;
        this.left = DominoImageLoader.loadDominoImage(s.getSquareLeft());
        this.right = DominoImageLoader.loadDominoImage(s.getSquareRight());
        afLeft = new AffineTransform();
        afRight = new AffineTransform();

        System.out.println(afLeft.isIdentity());
        System.out.println(afRight.isIdentity());

        int rowLeft = row;
        int colLeft = col;
        int rowRight = row;
        int colRight = col;

        switch(orientation) {
            case NORTH:
                rowLeft++;
                break;
            case EAST:
                afLeft.rotate(-ROT_ANGLE);
                afRight.rotate(ROT_ANGLE);
                colRight++;
                break;
            case SOUTH:
                afLeft.rotate(2*ROT_ANGLE);
                rowRight++;
                break;
            case WEST:
                afLeft.rotate(ROT_ANGLE);
                afRight.rotate(-ROT_ANGLE);
                colLeft++;
                break;
        }

        double tx = afLeft.getTranslateX();
        double ty = afLeft.getTranslateY();
        afLeft.translate(tx, ty);
        System.out.println(tx + ", " + ty);
        afLeft.rotate(0.1);
        afLeft.translate(-tx, -ty);
        
        afRight.translate(colRight * SIZE, rowRight * SIZE);
        afLeft.translate(colLeft * SIZE , rowLeft * SIZE );


        
    }

    //TODO consertar draw do domino
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(left, afLeft, null);
        g2d.drawImage(right, afRight, null);
    }
}
