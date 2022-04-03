import Generator.BirdGenerator;
import Generator.Config;
import Generator.FileManager;
import Models.Bird2D;
import Models.Parameters;
import Models.Vector2D;
import org.json.JSONArray;

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
        Parameters param = Config.getParameters();
        if (!sim.set_parameters(param))
            throw new RuntimeException("Invalid Parameters");



        sim.setup();
        assert param != null;
        String folder = FileManager.createStaticInfo(param);
        JSONArray prev_array = FileManager.generateDynamicFromBirds(sim.population, null);
        for (int step = 0; step < sim.total_steps; step++) {

            sim.update();
            JSONArray step_array = FileManager.generateDynamicFromBirds(sim.population, prev_array);
            prev_array = step_array;

        }
        FileManager.saveSnapshots(prev_array, folder);
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
                if (bird.distance(neighbor, space_width) <= radius)
                    close_neighbors.add(neighbor);
            }
            bird.change_theta(close_neighbors, random(-theta_amp, theta_amp));
        }

        for (Bird2D bird : population) {
            bird.update();
            bird.check_borders(space_width);
        }

    }

    double random(double min, double max) {
        Random rnd = new Random();
        return min + rnd.nextDouble() * (max-min);
    }


}
