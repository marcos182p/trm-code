package trm.model;

public class Solid {
	private Vector2D position;
	private Vector2D velocity;
	private double mass;
	
	public Solid(double mass){
		this.mass = mass;
		this.position = new Vector2D();
		this.velocity = new Vector2D();
	}
	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}
	public Vector2D getVelocity() {
		return velocity;
	}
	public Vector2D getPosition(){
		return position;
	}
	public double getMass() {
		return mass;
	}
	public void setPosition(Vector2D position){
		this.position = position;
	}
	public void updatePosition(){
		this.position = position.sum(velocity);
	}
	public void applyForce(Vector2D force){
		Vector2D accel = force.scalar(1.0/mass);
		velocity = velocity.sum(accel);
	}
}
