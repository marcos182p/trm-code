package trm.view.game.board;

import java.awt.Image;
import javax.swing.ImageIcon;
import trm.core.SquareNumber;

/**
 * @author TRM
 * @version 0.99
 */
public class DominoImageLoader {
    
    private static final String DEFAULT_NAME = "rsc//domino";
    private static final String EXTENSION = ".png";

    private DominoImageLoader () {
    }

    public static Image loadDominoSquareImage(SquareNumber sqrNumber) {
        String fullPath = DEFAULT_NAME + (sqrNumber.ordinal()) + EXTENSION;

        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
    public static Image loadDominoBack() {
        String fullPath = DEFAULT_NAME + "_back" + EXTENSION;
        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
}
