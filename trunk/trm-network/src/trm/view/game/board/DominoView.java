package trm.view.game.board;

import trm.view.game.utils.Orientation;
import trm.view.utils.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import trm.core.Stone;

/**
 * @author TRM
 * @version 0.99
 */
public class DominoView implements Drawable {

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
    public static final Image backLeft = DominoImageLoader.loadDominoBack();
    public static final Image backRight = DominoImageLoader.loadDominoBack();
    
    public DominoView(Stone s, final int row, final int col, Orientation orientation, Color c, boolean anchorRight) {
        this.left = DominoImageLoader.loadDominoSquareImage(s.getSquareLeft());
        this.right = DominoImageLoader.loadDominoSquareImage(s.getSquareRight());
        
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
                if(anchorRight) {
                    rowRight--;
                }else {
                    rowLeft++;
                }
                
                this.height += SIZE;
                break;
            case EAST:
                angleLeft = -ROT_ANGLE;
                angleRight = ROT_ANGLE;
                if(anchorRight) {
                    colRight++;
                }else {
                    colLeft--;
                }
                this.width += SIZE;
                break;
            case SOUTH:
                angleRight = 2*ROT_ANGLE;
                if(anchorRight) {
                    rowRight++;
                }else {
                    rowLeft--;
                }
                this.height += SIZE;
                break;
            case WEST:
                angleLeft = ROT_ANGLE;
                angleRight = -ROT_ANGLE;
                if(anchorRight) {
                    colRight--;
                }else {
                    colLeft++;
                }
                
                this.width += SIZE;
                break;
        }

        this.x = Math.min(colRight, colLeft)*SIZE;
        this.y = Math.min(rowRight, rowLeft)*SIZE;
        
        afRight.translate(colRight * SIZE, rowRight * SIZE);
        afLeft.translate(colLeft * SIZE , rowLeft * SIZE );
        afLeft.rotate(angleLeft,SIZE/2, SIZE/2);
        afRight.rotate(angleRight, SIZE/2, SIZE/2);

    }
    public void invert() {
        AffineTransform temp = this.afRight;
        this.afRight = this.afLeft;
        this.afLeft = temp;
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

    public static Image getBackImage() {
        BufferedImage bf = new BufferedImage(
                SIZE,
                SIZE*2,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bf.getGraphics();
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform f = new AffineTransform();
        g2d.drawImage(backLeft,0,0,SIZE, SIZE, null);
        g2d.drawImage(backRight, 0,SIZE, null);
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
