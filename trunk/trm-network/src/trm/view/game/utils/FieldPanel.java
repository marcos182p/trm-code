package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**Cria um painel com um label e um campo de texto ao lado*/
public class FieldPanel extends BGPanel{
	
	/**Variavel que indica a posicao do campo em relacao ao label
	 * no construtor, coloca o campo ao lado do label*/
	public static final int BY_SIDE = 0;
	/**Variavel que indica a posicao do campo em relacao ao label
	 * no construtor, coloca o campo abaixo do label*/
	public static final int BELOW = 1;
	private JLabel label;
	private JTextField box;
	private int fieldPosition;
	
	public FieldPanel(String bg, String label, int fieldColumns,  boolean isPassword, int fieldPosition) {
		super(bg);
		this.label = new JLabel(label);
		if(isPassword) {
			this.box = new JPasswordField(fieldColumns);
			
		}else {
			this.box = new JTextField(fieldColumns);
		}
		this.fieldPosition = fieldPosition;
		setup();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		box.setEnabled(enabled);
	}
	
	public void clear() {
		box.setText("");
	}
	public String getContent() {
		return box.getText();
	}
	public void setContent(String content) {
		this.box.setText(content);
	}
	private void setup() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.gridy = 0;
		c.insets = new Insets(5,5,5,5);
		add(label, c);
		c.weightx=1;
		if(fieldPosition == BELOW) {
			c.gridy = 1;
		}
		add(box, c);
	}
}
