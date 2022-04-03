package Models;

import java.util.List;

public class Bird2D {

    public Vector2D pos , vel , acc;
    double newTheta = 0 , oldTheta = 0;

    public Bird2D(Vector2D pos , Vector2D vel){
        this.pos = pos;
        this.vel = vel;
        oldTheta = vel.heading();
        this.acc = new Vector2D( 0 ,0 );
    }

    public double distance(Bird2D other) {
        return this.pos.distance(other.pos);
    }

    public void change_theta(List<Bird2D> birds, double random) {
        double total = this.oldTheta;
        for (Bird2D bird : birds) {
            total += bird.oldTheta;
        }

        this.newTheta = total / (birds.size() + 1) + random;
    }

    public void update() {
        this.pos = this.pos.add(vel);
        double mag = vel.magnitude();
        this.vel = new Vector2D(mag, 0).rotate(newTheta);
        oldTheta = newTheta;
    }
    @Override
    public String toString(){
        return "bird: {pos: " + pos + "\tvel: " + vel + "\tacc: " + acc +"}";
    }
}
