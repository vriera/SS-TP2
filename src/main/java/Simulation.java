import Models.Bird2D;
import Models.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    int total_agents = 2;
    double space_width = 100;
    double radius = 10;
    int total_steps = 2;
    double theta_amp = 0.3;
    double initial_v = 2;
    List<Bird2D> population;


    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.setup();

        for (int step = 0; step < sim.total_steps; step++) {
            sim.update();
        }
    }

    void setup() {
        population = new ArrayList<>();

        for (int i = 0; i < total_agents; i++) {
            Vector2D pos = new Vector2D(random(0, space_width), random(0, space_width));
            Vector2D vel = Vector2D.fromAngle(random(0, 2 * Math.PI), initial_v);
            population.add(new Bird2D(pos, vel));
            System.out.println("New bird at x: " + pos.x + " y: " + pos.y);
        }
    }

    void update() {
        // Generate new Grid
        int total_cells = (int)(space_width/radius);
        Grid2D grid = new Grid2D(total_cells, space_width);

        // Place each bird in its corresponding cell
        for (Bird2D bird : population) {
            grid.addBird(bird);
        }

        // Search for the neighbors of each bird

        for (Bird2D bird : population) {
            List<Bird2D> neighbors = grid.getNeighbors(bird);
            List<Bird2D> close_neighbors = new ArrayList<>();
            for (Bird2D neighbor : neighbors) {
                if (bird.distance(neighbor) <= radius)
                    close_neighbors.add(neighbor);
            }
            bird.change_theta(close_neighbors, random(-theta_amp, theta_amp));
        }

        for (Bird2D bird : population) {
            bird.update();
            System.out.println(bird);
        }

    }

    double random(double min, double max) {
        Random rnd = new Random();
        return min + rnd.nextDouble() * (max-min);
    }


}
