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

  void draw(float space_width, float radius, boolean show_radius, boolean color_from_angle) {
    pushMatrix();
    if (color_from_angle) {
      fill(map(this.angle, 0, TWO_PI, 0, 255), 255, 255);
    } else {
      fill(255, 0, 0, 150);
    }
    noStroke();

    float x_canvas = map(pos.x, 0, space_width, 0, width);
    float y_canvas = map(pos.y, 0, space_width, 0, height);

    float w = map(radius, 0, space_width, 0, width);

    translate(x_canvas, y_canvas);
    rotate(this.angle - PI/2);
    triangle(-w/8, -w/4, 0, w/2 - w/4, w/8, -w/4);
    if (show_radius) {
      noFill();
      stroke(#24FAFF);
      ellipse(0, 0, w, w);
    }
    popMatrix();
  }
}
