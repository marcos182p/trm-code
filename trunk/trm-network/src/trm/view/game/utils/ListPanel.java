package trm.view.game.utils;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class ListPanel extends BGPanel{

	private JLabel label;
	private JList elementList;
	
	public ListPanel(String bg, String label, DefaultListModel model) {
		super(bg);
		this.label = new JLabel(label);
		this.elementList = new JList(model);
		
		setup();
	}
	
	public String getSelectedElement() {
		return (String)elementList.getSelectedValue();
	}
	
	public ListModel getModel() {
		return elementList.getModel();
	}
	
	private void setup() {
		
		JScrollPane pane = new JScrollPane(elementList);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.weighty = 0.01;
		add(label, c);
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		add(pane, c);
	}
}
