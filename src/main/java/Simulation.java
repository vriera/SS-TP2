import Models.Bird2D;
import Models.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    int total_agents = 10;
    double space_width = 100;
    double radius = 5;
    int total_steps = 100;
    double theta_amp = 0.3;
    double initial_v = 2;
    List<Bird2D> population;


    public static void main(String[] args) {
        Vector2D v = new Vector2D(2, 0);
        Vector2D v2 = v.rotate(Math.PI/4);
        System.out.println("X: " + v2.x + ", Y: " + v2.y);
    }

    void setup() {
        population = new ArrayList<>();

        for (int i = 0; i < total_agents; i++) {
            Vector2D pos = new Vector2D(random(0, space_width), random(0, space_width));
            Vector2D vel = Vector2D.fromAngle(random(0, 2 * Math.PI), initial_v);
            population.add(new Bird2D(pos, vel));
        }
    }

    void update() {
        
    }

    double random(double min, double max) {
        Random rnd = new Random();
        return min + rnd.nextDouble() * (max-min);
    }


}
