package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;


/**Cria um painel com b�rios bot�es
 * seguidos e espa�ados*/
public class ButtonPanel extends BGPanel{
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	private List<JButton> buttons;
	private int orientation;
	
	public ButtonPanel(String bg, String... buttonNames) {
		this(bg, HORIZONTAL, buttonNames);
	}
	
	public ButtonPanel(String bg, int orientation, String... buttonNames) {
		super(bg);
		this.buttons = new ArrayList<JButton>();
		this.orientation = orientation;
		for(String name : buttonNames) {
			buttons.add(new JButton(name));
		}
		setup();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		for(JButton b : buttons) {
			b.setEnabled(enabled);
		}
	}
	public JButton getButton(String buttonName) {
		for(JButton b : buttons) {
			if(b.getText().equals(buttonName)) {
				return b;
			}
		}
		return null;
	}
	public void setup() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		int jumpx = 0;
		int jumpy = 0;
		if(orientation == HORIZONTAL) {
			jumpx = 1;
		}else if(orientation == VERTICAL) {
			jumpy = 1;
		}
		for(JButton button : buttons) {
			c.gridx += jumpx;
			c.gridy += jumpy;
			add(button, c);
		}
	}
}
