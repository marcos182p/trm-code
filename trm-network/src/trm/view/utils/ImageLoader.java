package trm.view.utils;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author TRM
 * @version 0.99
 */
public class ImageLoader {

    private static final String DEFAULT_PATH = "rsc//";

    private ImageLoader() {}

    public static Image loadBackgroundImage(String name) {
        String fullPath = DEFAULT_PATH + name;

        Image dominoImage = new ImageIcon(fullPath).getImage();
        return dominoImage;
    }
}
