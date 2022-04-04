JSONObject config;
ArrayList<SnapshotData> snapshots_data;

Bird birds;
int total_steps;
int total_birds;
int sim_frame_rate;
float radius;
float space_width;
boolean show_radius;
boolean color_from_angle;
boolean show_trail;
boolean save_animation;
int step = 0;
String result_name;

void setup() {
  size(800, 800);
  background(0);
  
  JSONObject dynamic_data;
  JSONObject static_data;
  
  config = loadJSONObject("../visualization_config.json");
  result_name = config.getString("result_name");
  sim_frame_rate = config.getInt("frame_rate");
  show_radius = config.getBoolean("show_radius");
  color_from_angle = config.getBoolean("color_from_angle");
  show_trail = config.getBoolean("show_trail");
  save_animation = config.getBoolean("save_animation");
  
  println(show_trail);
  
  frameRate(sim_frame_rate);
  if (color_from_angle) {
    colorMode(HSB);
  }
  
  static_data = loadJSONObject("../results/" + result_name + "/static.json");
  dynamic_data = loadJSONObject("../results/" + result_name + "/snapshots.json");
  
  snapshots_data = new ArrayList<>();
  
  loadStatic(static_data);
  loadDynamic(dynamic_data);
  
  println(snapshots_data.get(1).pos.get(0));
}

void draw() {
  if (show_trail) {
    fill(0, 100);
    rect(0,0,width,height);
  } else {
    background(20);
  }
  step++;
  Bird b = new Bird();
  SnapshotData current_snap = snapshots_data.get(step % snapshots_data.size());
  for (int i = 0; i < current_snap.pos.size(); i++) {
    b.setPos(current_snap.pos.get(i));
    b.setVel(current_snap.vel.get(i));
    b.setAngle(current_snap.ang.get(i));
    b.draw(space_width, radius, show_radius, color_from_angle);
  }
  
  fill(255);
  text("Name: " +  config.getString("result_name"), 10, 10);
  text("Total Agents: " + total_birds, 10, 24);
  text("Space Width: " + space_width, 10, 38);
  text("Step " + (step % total_steps) + "/" + total_steps, 10, 52);
  if (step >= total_steps) {
    noLoop();
  }
  if (save_animation) {
    saveFrame("outputs/" + result_name + "/frame####.png");
  }
}

void loadStatic(JSONObject params) {
  total_steps = params.getInt("total_steps");
  total_birds = params.getInt("total_agents");
  radius = params.getFloat("radius");
  space_width = params.getFloat("space_width");
}

void loadDynamic(JSONObject params) {
  JSONArray snapshots = params.getJSONArray("snapshots");
  for (int i = 0; i < snapshots.size(); i++) {
    ArrayList<PVector> positions = new ArrayList<>();
    ArrayList<PVector> velocities = new ArrayList<>();
    ArrayList<Float> angles = new ArrayList<>();
    JSONArray data = snapshots.getJSONObject(i).getJSONArray("snapshot");
    for (int j = 0; j < total_birds; j++) {
      JSONObject snap_data = data.getJSONObject(j);
      PVector pos = get_vec(snap_data.getJSONObject("pos"));
      PVector vel = get_vec(snap_data.getJSONObject("vel"));
      float ang = snap_data.getFloat("heading");
      
      positions.add(pos);
      velocities.add(vel);
      angles.add(ang);
    }
    snapshots_data.add(new SnapshotData(positions, velocities, angles));
  }
}

PVector get_vec(JSONObject pos) {
  return new PVector(pos.getFloat("x"), pos.getFloat("y"));
}

void keyPressed() {
  loop();
}
