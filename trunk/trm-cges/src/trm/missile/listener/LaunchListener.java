package trm.missile.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import trm.environment.impl.Environment;

import trm.missile.view.DrawPanel;
import trm.missile.view.MissileView;
import trm.model.Canon;
import trm.model.Missile;

public class LaunchListener implements ActionListener{

	private Canon canon;
	private DrawPanel panel;
	private Environment environment;
	
	public LaunchListener(DrawPanel panel, Canon canon, Environment environment) {
		this.canon = canon;
		this.panel = panel;
		this.environment = environment;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Missile missile = canon.shootMissile(environment);
		if(missile != null) {
			panel.addDrawable(new MissileView(missile));
		}
	}

}
