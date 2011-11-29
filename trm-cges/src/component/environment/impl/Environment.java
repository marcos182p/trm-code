package component.environment.impl;

import java.util.ArrayList;
import java.util.List;

import trm.model.Solid;

public class Environment extends Subject{
	private List<Solid> elements;
	private boolean running;
	private long delay;
	private Thread runCycle;
	
	public Environment() {
		elements = new ArrayList<Solid>();
		running = false;
		setFPS(60);
		
		runCycle = new Thread(
				new Runnable() {
					public void run() {
						while(running) {
							try {
								Thread.sleep(delay);
								for(Solid solid : elements) {
									solid.applyForce(Physics.getForce(solid));
								}
								for(Solid solid : elements) {
									solid.updatePosition();
								}
								notifyObservers();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				});
	}
	
	public void addSolid(Solid solid) {
		this.elements.add(solid);
	}
	public void removeSolid(Solid solid) {
		this.elements.remove(solid);
	}
	public void setFPS(long fps){
		this.delay = 1000/fps;
	}
	public void start() {
		 running = true;
		 runCycle.start();
	}
	public void stop() {
		running = false;
	}
	
}
