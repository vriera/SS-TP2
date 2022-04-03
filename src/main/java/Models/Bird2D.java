package Models;

import java.util.List;

public class Bird2D {

    public Vector2D pos , vel;
    double newTheta = 0 , oldTheta = 0;

    public Bird2D(Vector2D pos , Vector2D vel){
        this.pos = pos;
        this.vel = vel;
        oldTheta = vel.heading();
    }

    public double distance(Bird2D other, double board_width) {
        double distX = Math.abs(this.pos.x - other.pos.x);
        double distY = Math.abs(this.pos.y - other.pos.y);
        if (distX > board_width/2) {
            distX -= board_width;
        }
        if (distY > board_width/2) {
            distY -= board_width;
        }
        return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
    }

    public void change_theta(List<Bird2D> birds, double random) {
        double total = 0;
        Vector2D v = new Vector2D(0, 0);
        for (Bird2D bird : birds) {
            //si
            v = v.add(bird.vel.normalize());
        }

        this.newTheta = ((v.heading()) + random);
    }

    public void update() {
        this.pos = this.pos.add(vel);
        double mag = vel.magnitude();
        this.vel = Vector2D.fromAngle(newTheta, mag);
        oldTheta = newTheta;
    }

    public void check_borders(double width) {
        if (pos.x >= width) {
            pos = pos.sub(new Vector2D(width, 0));
        } else if (pos.x < 0) {
            pos = pos.add(new Vector2D(width, 0));
        }

        if (pos.y >= width) {
            pos = pos.sub(new Vector2D(0, width));
        } else if (pos.y < 0) {
            pos = pos.add(new Vector2D(0, width));
        }
    }

    @Override
    public String toString(){
        return "bird: {pos: " + pos + "\tvel: " + vel;
    }

    private static double constrain_angle(double angle) {
        while (angle < 0) {
            angle += Math.PI * 2;
        }
        while (angle >= (2 * Math.PI)) {
            angle -= Math.PI * 2;
        }
        return angle;
    }

}
