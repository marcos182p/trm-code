package trm.model;

import component.environment.impl.Environment;

public class Canon extends Solid {

    private double angle;
    private double intensity;
    private double missileMass;

    public Canon(double missileMass) {
        super(1);
        this.missileMass = missileMass;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setAngleDegrees(double angle) {
        this.angle = angle * Math.PI / 180;
    }

    public double getAngle() {
        return angle;
    }

    public double getAngleDegrees() {
        return angle * 180 / Math.PI;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public double getIntensity() {
        return intensity;
    }

    public Missile shootMissile(Environment environment) {
        Missile missile = new Missile(missileMass);
        missile.setPosition(getPosition());
        missile.setInitialCondition(angle, intensity);
        environment.addSolid(missile);
        return missile;
    }
}
