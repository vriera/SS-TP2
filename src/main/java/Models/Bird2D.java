package Models;

public class Bird2D {

    public Vector2D pos , vel , acc;
    double newTheta = 0 , oldTheta = 0;

    public Bird2D(Vector2D pos , Vector2D vel){
        this.pos = pos;
        this.vel = vel;
        this.acc = new Vector2D( 0 ,0 );

    }
    @Override
    public String toString(){
        return "bird: {pos: " + pos + "\tvel: " + vel + "\tacc: " + acc +"}";
    }
}
