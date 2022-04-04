package Models;

public class Parameters {
    public Integer total_agents;
    public Integer total_steps;
    public Double space_width;
    public Double velocity;
    public Double radius;
    public Double theta_amp;
    public String folder;
    public Double runs;

    public Parameters(Integer total_agents, Integer total_steps, Double space_width, Double velocity, Double radius, Double theta_amp, String folder , Double runs) {
        this.total_agents = total_agents;
        this.total_steps = total_steps;
        this.space_width = space_width;
        this.velocity = velocity;
        this.radius = radius;
        this.theta_amp = theta_amp;
        this.folder = folder;
        this.runs = runs;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "total_agents=" + total_agents +
                ", total_steps=" + total_steps +
                ", space_width=" + space_width +
                ", velocity=" + velocity +
                ", radius=" + radius +
                ", theta_amp=" + theta_amp +
                ", folder='" + folder + '\'' +
                ", runs=" + runs +
                '}';
    }
}
