package trm.view.game.utils;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.UIManager;


/**Cria um painel com uma imagem ao fundo
 * a imagem deve estar em uma pasta <i>rsc</i>
 * e se o tipo da imagem n�o for especificado, assume-se
 * que seja .jpg*/
public class BGPanel extends JPanel{
	
	private Image bgImage;
	public BGPanel(String bg) {
		this.bgImage = ImageLoader.load(bg);
		
		if(bgImage.getWidth(null) == -1) {
			if(bg != null) {
				int indexOfExtension = bg.lastIndexOf('.');
				if(indexOfExtension != -1) {
					String extension = bg.substring(bg.lastIndexOf('.')+1);
					String name = bg.substring(0,bg.lastIndexOf('.'));
					bgImage = ImageLoader.load(name, extension);
				}
			}
			
		}
	}

    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
	}
}
