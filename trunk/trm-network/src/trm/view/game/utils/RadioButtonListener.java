package trm.view.game.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonListener implements ActionListener{
	private RadioButtonPanel panel;
	private JRadioButton button;
	
	public RadioButtonListener(RadioButtonPanel panel, JRadioButton button) {
		this.panel = panel;
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	    this.panel.clear();
	    this.panel.select(button);
	}
}
