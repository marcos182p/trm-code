/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trm.view.utils;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author rafanet
 */
public class ImageLoader {
    private static final String DEFAULT_PATH = "rsc//";

    private ImageLoader () {
    }

    public static Image loadBackgroundImage(String name) {
        String fullPath = DEFAULT_PATH + name;

        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
}
