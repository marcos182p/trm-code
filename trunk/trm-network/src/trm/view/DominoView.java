/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import trm.core.Stone;

/**
 *
 * @author marcos182p
 */
public class DominoView implements Drawable{

    public static final int SIZE = 32;//peca com 32 pixels
    private static final double ROT_ANGLE = Math.PI/2;
    private Image left;
    private Image right;
    private AffineTransform afLeft;
    private AffineTransform afRight;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color proeminentColor;
    private int colorOpacity;
    
    public DominoView(Stone s, final int row, final int col, Orientation orientation, Color c) {
        this.left = DominoImageLoader.loadDominoSquareImage(s.getSquareLeft());
        this.right = DominoImageLoader.loadDominoSquareImage(s.getSquareRight());
        this.x = col * SIZE;
        this.y = row * SIZE;
        this.width = SIZE;
        this.height = SIZE;

        afLeft = new AffineTransform();
        afRight = new AffineTransform();
        
        colorOpacity = 50;
        setColor(c);
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
                this.height += SIZE;
                break;
            case EAST:
                angleLeft = -ROT_ANGLE;
                angleRight = ROT_ANGLE;
                colRight++;
                this.width += SIZE;
                break;
            case SOUTH:
                angleRight = 2*ROT_ANGLE;
                rowRight++;
                this.height += SIZE;
                break;
            case WEST:
                angleLeft = ROT_ANGLE;
                angleRight = -ROT_ANGLE;
                colLeft++;
                this.width += SIZE;
                break;
        }
        
        afRight.translate(colRight * SIZE, rowRight * SIZE);
        afLeft.translate(colLeft * SIZE , rowLeft * SIZE );
        afLeft.rotate(angleLeft,SIZE/2, SIZE/2);
        afRight.rotate(angleRight, SIZE/2, SIZE/2);
    }

    public void setColorOpacity(int alpha) {
        this.colorOpacity = alpha;
        setColor(proeminentColor);
    }

    private void setColor(Color playerColor) {
        this.proeminentColor = new Color(
                playerColor.getRed(),
                playerColor.getGreen(),
                playerColor.getBlue(),
                colorOpacity
                );
    }

    public Image getFullImage() {
        BufferedImage bf = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = bf.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(left, afLeft, null);
        g2d.drawImage(right,afRight,null);
        g2d.setColor(proeminentColor);
        g2d.fillRect(x, y, width, height);
        g = g2d.create();

        return bf;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(left, afLeft, null);
        g2d.drawImage(right, afRight, null);
        g2d.setColor(proeminentColor);
        g2d.fillRect(x, y, width, height);
    }
}
