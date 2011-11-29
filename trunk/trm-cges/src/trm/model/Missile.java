package trm.model;

public class Missile extends Solid{

	public Missile(double mass) {
		super(mass);
	}
	
	public void setInitialCondition(double angle, double velocity) {
		setVelocity(new Vector2D(Math.cos(angle)*velocity, Math.sin(angle)*velocity));
	}
	public void setInitialConditionDegrees(double angle, double velocity) {
		setInitialCondition(angle*Math.PI/180, velocity);
	}
	
	public double getAngle() {
		if(getVelocity().module() == 0) {
			return 0;
		}else {
			double cos = getVelocity().normalized().x;
			double sin = getVelocity().normalized().y;
			double angle = Math.acos(cos);
			if(sin < 0){
				angle = 2*Math.PI - angle;
			}
			return angle;
		}
	}

}
