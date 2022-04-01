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

        public Double distance(Vector2D other){
            return Math.sqrt( (Math.pow(x - other.x , 2) + Math.pow(y-other.y , 2) ));
        }

        public Vector2D rotate(double angle) {
            double current_angle = this.heading();
            current_angle += angle;
            while (angle <= 0) {
                angle += 2 * Math.PI;
            }
            while (angle > 2 * Math.PI) {
                angle -= 2 * Math.PI;
            }
            double mag = Math.sqrt(this.x * this.x + this.y * this.y);
            double new_x = Math.cos(current_angle) * mag;
            double new_y = Math.sin(current_angle) * mag;
            return new Vector2D(new_x, new_y);
        }

        public double heading() {
            return Math.atan2(this.y, this.x);
        }

        public static Vector2D fromAngle(double angle, double magnitude) {
            return new Vector2D(magnitude, 0).rotate(angle);
        }

}
