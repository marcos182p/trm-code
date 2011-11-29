package component.environment.impl;

import trm.model.Solid;
import trm.model.Vector2D;

public class Physics {
	private static double G = 0.2;
	
	public static Vector2D getForce(Solid solid) {
		return new Vector2D(0,1).scalar(solid.getMass()*G);
	}
}
