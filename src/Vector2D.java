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

}
