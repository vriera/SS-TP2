package Generator;

import Models.Bird2D;
import Models.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class BirdGenerator {
    public static List<Bird2D> generateBirds(int total_agents , double space_width , double velocity) {
        List<Bird2D> birds = new ArrayList<>();
        for( int i = 0 ; i < total_agents ; i++){
            double x_pos = Math.random() * space_width;
            double y_pos = Math.random() * space_width;

            Vector2D pos = new Vector2D(x_pos , y_pos);

//            double x_vel = (Math.random() - 0.5) *2;
//            double y_vel = (Math.random() - 0.5) *2;
            double ang = Math.random() * 2 * Math.PI;
            Vector2D vel = Vector2D.fromAngle(ang, velocity);

            birds.add(new Bird2D(pos , vel));

        }
        return birds;
    }

}
