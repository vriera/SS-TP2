import Generator.BirdGenerator;
import Generator.Config;
import Models.Bird2D;
import Models.Parameters;
import Models.Vector2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulation {
    int total_agents;
    double space_width;
    double radius;
    int total_steps;
    double theta_amp;
    double velocity;
    String folder;
    List<Bird2D> population;


    public static void main(String[] args) {
        Simulation sim = new Simulation();
        if (!sim.set_parameters(Config.getParameters()))
            throw new RuntimeException("Invalid Parameters");
        System.out.println(sim.total_agents);

        sim.setup();
        for (int step = 0; step < sim.total_steps; step++) {
            sim.update();
        }
    }

    boolean set_parameters(Parameters parameters) {
        if (parameters == null)
            return false;
        this.total_agents = parameters.total_agents;
        this.total_steps = parameters.total_steps;
        this.space_width = parameters.space_width;
        this.velocity = parameters.velocity;
        this.radius = parameters.radius;
        this.theta_amp = parameters.theta_amp;
        this.folder = parameters.folder;
        return true;
    }

    void setup() {
        population = BirdGenerator.generateBirds(total_agents, space_width, velocity);
        System.out.println(this.total_agents);
//        population = new ArrayList<>();
//
//        for (int i = 0; i < total_agents; i++) {
//            Vector2D pos = new Vector2D(random(0, space_width), random(0, space_width));
//            Vector2D vel = Vector2D.fromAngle(random(0, 2 * Math.PI), velocity);
//            population.add(new Bird2D(pos, vel));
//            System.out.println("New bird at x: " + pos.x + " y: " + pos.y);
//        }
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
            bird.check_borders(space_width);
            System.out.println(bird);
        }

    }

    double random(double min, double max) {
        Random rnd = new Random();
        return min + rnd.nextDouble() * (max-min);
    }


}
