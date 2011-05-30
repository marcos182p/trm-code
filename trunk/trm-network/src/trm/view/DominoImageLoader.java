/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Image;
import javax.swing.ImageIcon;
import trm.core.SquareNumber;

/**
 *
 * @author marcos182p
 */
public class DominoImageLoader {
    
    private static final String DEFAULT_NAME = "rsc//domino";
    private static final String EXTENSION = ".png";

    private DominoImageLoader () {
    }

    public static Image loadDominoImage(SquareNumber sqrNumber) {
        String fullPath = DEFAULT_NAME + (sqrNumber.ordinal()) + EXTENSION;

        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
}
