/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rafanet
 */
public class BackgroundImageLoader {
    private static final String DEFAULT_PATH = "rsc//";
    private static final String EXTENSION = ".png";

    private BackgroundImageLoader () {
    }

    public static Image loadBackgroundImage(String name) {
        String fullPath = DEFAULT_PATH + (name) + EXTENSION;

        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
}
