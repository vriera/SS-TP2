class Bird {
  PVector pos, vel;
  float angle;
  
  void setPos(float x, float y) {
    this.pos = new PVector(x, y);
  }
  
  void setPos(PVector p) {
    setPos(p.x, p.y);
  }
  
  void setVel(float x, float y) {
    this.vel = new PVector(x, y);
  }
  
  void setVel(PVector v) {
    setVel(v.x, v.y);
  }
  
  void setAngle(float ang) {
    this.angle = ang;
  }
  
  void draw(float space_width) {
    pushMatrix();
    fill(255, 0, 0);
    noStroke();
    
    float x_canvas = map(pos.x, 0, space_width, 0, width);
    float y_canvas = map(pos.y, 0, space_width, 0, height);
    
    
    
    translate(x_canvas, y_canvas);
    rotate(-this.angle);
    triangle(-10, 0, 0, 30, 10, 0);
    popMatrix();
  }
}
