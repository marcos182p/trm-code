package trm.core;

import trm.core.data.Polygon;
import trm.core.data.Solid;
import trm.core.data.Vector3D;

/**
 *
 * @author mpjms
 */
public class Camera {
    private Vector3D position;
    private Vector3D center;
    private Vector3D up;
    private Vector3D origin;
    private Vector3D bounds;
    private Vector3D cameraSize;
    
    public Camera() {
        position = new Vector3D(0,0,0);
        center = new Vector3D(0,0,-1);
        up = new Vector3D(0,1,0);
        origin = null;
        bounds = null;
        cameraSize = new Vector3D(0,0,0);
    }

    public Camera(Vector3D position, Vector3D center, Vector3D up) {
        this.position = position;
        this.center = center;
        this.up = up;
        origin = null;
        bounds = null;
        cameraSize = new Vector3D(0,0,0);
    }

    public void setBoundingBox(float x, float y, float z, float width, float height, float lenght) {
        origin = new Vector3D(x, y, z);
        bounds = new Vector3D(width, height, lenght);
    }

    public void setCameraSize(float w, float h, float l) {
        this.cameraSize = new Vector3D(w, h, l);
    }

    public Vector3D getCenter() {
        return center;
    }

    public Vector3D getPosition() {
        return position;
    }

    public Vector3D getUp() {
        return up;
    }

    public void moveVertically(float velocity) {

        if(origin != null && bounds != null) {
            if(position.getY() + velocity - cameraSize.getY() < origin.getY()) {
                velocity = -position.getY() + cameraSize.getY();
            }
            if(position.getY() + velocity + cameraSize.getY() > origin.getY() + bounds.getY()) {
                velocity = origin.getY() + bounds.getY() - cameraSize.getY() - position.getY();
            }
        }

        this.position.setY(position.getY() + velocity);
        this.center.setY(center.getY() + velocity);
    }

    public void moveHorizontally(float velocity) {
        Vector3D dir = center.getSub(position);
        Vector3D axis = Vector3D.outerProduct(dir, up);
        axis = axis.getNormalizedVector().getScalar(velocity);

         if(origin != null && bounds != null) {
            if(position.getX() + axis.getX() - cameraSize.getX() < origin.getX()) {
                axis.setX(origin.getX() - position.getX() + cameraSize.getX());
            }
            if(position.getZ() + axis.getZ() -cameraSize.getZ() < origin.getZ()) {
                axis.setZ(origin.getZ() - position.getZ() + cameraSize.getZ());
            }
            if(position.getX() + axis.getX() + cameraSize.getX() > origin.getX() + bounds.getX()) {
                axis.setX(origin.getX() + bounds.getX() - position.getX() - cameraSize.getX());
            }
            if(position.getZ() + axis.getZ() + cameraSize.getZ() > origin.getZ() + bounds.getZ()) {
                axis.setZ(origin.getZ() + bounds.getZ() - position.getZ() - cameraSize.getZ());
            }
        }

        this.position = position.getSum(axis);
        this.center = center.getSum(axis);
    }

    public void move(float velocity){
        Vector3D vel = center.getSub(position).getNormalizedVector().getScalar(velocity);

        if(origin != null && bounds != null) {
            if(position.getX() + vel.getX() - cameraSize.getX() < origin.getX()) {
                vel.setX(origin.getX() - position.getX() + cameraSize.getX());
            }
            if(position.getZ() + vel.getZ() - cameraSize.getZ() < origin.getZ()) {
                vel.setZ(origin.getZ() - position.getZ() + cameraSize.getZ());
            }
            if(position.getX() + vel.getX() + cameraSize.getX() > origin.getX() + bounds.getX()) {
                vel.setX(origin.getX() + bounds.getX() - position.getX() - cameraSize.getX());
            }
            if(position.getZ() + vel.getZ() + cameraSize.getZ() > origin.getZ() + bounds.getZ()) {
                vel.setZ(origin.getZ() + bounds.getZ() - position.getZ() - cameraSize.getZ());
            }
        }

        this.position.setX(position.getX()+ vel.getX());
        this.position.setZ(position.getZ() + vel.getZ());
        this.center.setX(center.getX() + vel.getX());
        this.center.setZ(center.getZ() + vel.getZ());

        
        
    }

    public void turn(float rad) {
        Vector3D vel = center.getSub(position).getNormalizedVector();
        vel.setX((float)(vel.getX()*Math.cos(rad) + Math.sin(rad)*vel.getZ()));
        vel.setZ((float)(vel.getZ()*Math.cos(rad) - Math.sin(rad)*vel.getX()));
        center.setX(position.getX() + vel.getX());
        center.setZ(position.getZ() + vel.getZ());

    }
}
