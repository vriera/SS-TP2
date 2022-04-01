import java.util.ArrayList;
import java.util.List;

public class Grid2D {
    public int size;
    public double width;
    List<Bird2D>[][] grid;

    public Grid2D(int size, double width) {
        this.size = size;
        this.width = width;
        this.grid = new ArrayList[size][size];
    }

    public void addBird(Bird2D bird) {
        double x = bird.pos.x;
        double y = bird.pos.y;
        int gridX = (int)(x * width / size);
        int gridY = (int)(y * width / size);

        if (grid[gridX][gridY] == null) {
            grid[gridX][gridY] = new ArrayList<Bird2D>();
        }
        grid[gridX][gridY].add(bird);
    }

    public void addBirds(List<Bird2D> birds) {
        for (Bird2D b : birds) {
            addBird(b);
        }
    }

    public List<Bird2D> getBirds(int x, int y) {
        return grid[x][y];
    }
}
