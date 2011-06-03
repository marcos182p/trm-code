package trm.view.game.utils;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
/**Carrega uma imagem da pasta </i>rsc<i>*/
public class ImageLoader {
	private static final String PATH = "rsc//";
	private static Map<String, Image> images = new HashMap<String, Image>();
	private static final String DEFAULT_EXTENSION = "jpg";
	/**Carrega uma imagem com extens�o jpg*/
	public static Image load(String name) {
		
		String path = PATH + name + "." + DEFAULT_EXTENSION;
		if(!images.containsKey(path)) {
			Image im = new ImageIcon(path).getImage();
			images.put(path, im);
		}
		return images.get(path);
	}
	/**Carrega uma imagem com extens�o espec�fica*/
	public static Image load(String name, String extension) {
		String path = PATH + name + "." + extension;
		if(!images.containsKey(path)) {
			Image im = new ImageIcon(path).getImage();
			images.put(path, im);
		}
		return images.get(path);
	}
}
