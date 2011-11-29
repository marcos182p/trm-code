package trm.missile.window;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import component.environment.impl.Environment;

import trm.missile.listener.LaunchListener;
import trm.missile.view.DrawPanel;
import trm.missile.view.PositionPanel;
import trm.model.Canon;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private DrawPanel panel;
	private PositionPanel initialPosition;
	private PositionPanel targetPosition;
	private JButton launch;

	public MainWindow(Canon canon, int w, int h, Environment environment) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(w, h);
		panel = new DrawPanel();
		initialPosition = new PositionPanel();
		targetPosition = new PositionPanel();
		launch = new JButton("Launch");
		launch.addActionListener(new LaunchListener(panel, canon,environment));
		setup();
	}
	
	public DrawPanel getPanel() {
		return panel;
	}
	
	private void setup(){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 20;
		c.insets = new Insets(5,5,5,5);
		c.fill = GridBagConstraints.BOTH;
		//------------------------
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBorder(BorderFactory.createTitledBorder("Simulation"));
		container.add(panel, c);
		add(container, c);
		//------------------------
		c.gridheight = 1;
		c.gridx = 2;
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		initialPosition.setBorder(BorderFactory.createTitledBorder("Initial Position"));
		add(initialPosition, c);
		//------------------------
		c.gridy++;
		targetPosition.setBorder(BorderFactory.createTitledBorder("Target Position"));
		add(targetPosition, c);
		//------------------------
		c.gridy++;
		add(launch, c);
		//------------------------
		c.gridy++;
		c.weighty = 50;
		c.fill = GridBagConstraints.BOTH;
		add(new JPanel(), c);
	}
	
	public void open() {
		setVisible(true);
	}
}
