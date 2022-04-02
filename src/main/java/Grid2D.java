import Models.Bird2D;

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
        int gridX = (int)(x / (width / size));
        int gridY = (int)(y / (width / size));

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

    public List<Bird2D> getNeighbors(Bird2D bird) {
        ArrayList<Bird2D> neighbors = new ArrayList<>();
        double x = bird.pos.x;
        double y = bird.pos.y;
        int gridX = (int)(x / (width / size));
        int gridY = (int)(y / (width / size));
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int index_i = Math.floorMod((gridX + i), size);
                int index_j = Math.floorMod((gridY + j), size);
                if (grid[index_i][index_j] == null)
                    continue;
                neighbors.addAll(grid[index_i][index_j]);
            }
        }
        neighbors.remove(bird);
        return neighbors;
    }
}
