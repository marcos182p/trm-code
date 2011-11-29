package trm.missile.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import component.environment.impl.Observer;


public class DrawPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;
	private List<Drawable> drawables;
	
	public DrawPanel() {
		drawables = new ArrayList<Drawable>();
	}
	public void addDrawable(Drawable drawable) {
		this.drawables.add(drawable);
	}
	public void removeDrawable(Drawable drawable) {
		this.drawables.remove(drawable);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		for(Drawable drawable : drawables) {
			drawable.draw(g2D, getWidth(), getHeight(),this);
		}
	}
	
	public void update() {
		repaint();
	}
}
