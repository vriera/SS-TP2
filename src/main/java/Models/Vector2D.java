package Models;

import java.util.Vector;

public class Vector2D {

        public final double x;
        public final double y;

        public Vector2D(double x , double y) {
            this.x = x;
            this.y = y;
        }

        public Vector2D add(Vector2D other) {
            return new Vector2D(x + other.x, y + other.y);
        }

        public Vector2D sub(Vector2D other) {
            return new Vector2D(x - other.x, y - other.y);
        }

        public Vector2D mul(Double alpha) {
            return new Vector2D(x * alpha, y * alpha);
        }

        public Vector2D normalize(){
            double factor = this.distance(new Vector2D(0 , 0));
            return new Vector2D(x/factor , y/factor);
        }

        public Double distance(Vector2D other){
            return Math.sqrt( (Math.pow(x - other.x , 2) + Math.pow(y-other.y , 2) ));
        }

        @Override
        public String toString(){
            return String.format("( %f , %f )" , x ,y);
        }

}
