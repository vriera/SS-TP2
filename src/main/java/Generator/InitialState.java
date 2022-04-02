package Generator;


import Models.Bird2D;
import Models.Vector2D;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialState {
    private final static String DIRECTORY = "./results";
    public static void main(String[] args) {
        String folder = createStaticInfo(10 , 10 , 100 , 10.0 , 0.5 , 0.5 , null);
        List<Bird2D> birds = generateDynamic(10 , 10 , 100 );
        JSONArray array = generateDynamicFromBirds(birds , null );
        saveSnapshots(array , folder);
    }

    public static void saveSnapshots( JSONArray snapshots , String folder){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("snapshots" , snapshots);
        String filePath = DIRECTORY + "/" + folder + "/snapshots.json";
        System.out.println(filePath);
        File myObj = new File(filePath);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter(filePath );
                myWriter.write(jsonObject.toString());
                System.out.println(jsonObject);
                myWriter.close();
            }else{
                System.out.println("File not created");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String createStaticInfo(int total_agents , int space_width , int total_steps , double velocity, double radius , double theta_amp , String folder) {
        if(folder == null){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss");
            LocalDateTime time = LocalDateTime.now();
            folder = dtf.format(time);
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("total_agents" , total_agents);
        jsonObject.put("space_width" , space_width);
        jsonObject.put("radius" , radius);
        jsonObject.put("theta_amp" , theta_amp);
        jsonObject.put("total_steps" , total_steps);
        jsonObject.put("velocity" , velocity);
        String filePath = DIRECTORY + "/" + folder + "/static.json";
        File dir = new File(DIRECTORY + "/" + folder);
        dir.mkdir();
        System.out.println(filePath);
        File myObj = new File(filePath);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileWriter myWriter = new FileWriter(filePath );
                myWriter.write(jsonObject.toString());
                System.out.println(jsonObject);
                myWriter.close();
            }else{
                System.out.println("File not created");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return folder;
    }

    public static JSONArray generateDynamicFromBirds( List<Bird2D> birds , JSONArray array ){

        if (array == null)
                array = new JSONArray();

        JSONObject snapshot = new JSONObject();
        JSONArray this_array = new JSONArray();
        for ( Bird2D bird: birds) {
            JSONObject pos_json = new JSONObject();
            pos_json.put("x" , bird.pos.x);
            pos_json.put("y" , bird.pos.y);
            JSONObject vel_json = new JSONObject();
            vel_json.put("x" , bird.vel.x);
            vel_json.put("y" , bird.vel.y);
            JSONObject bird_json = new JSONObject();
            bird_json.put("pos" , pos_json);
            bird_json.put("vel" , vel_json);
            this_array.put(bird_json);
        }
        snapshot.put("snapshot" , this_array);
        array.put(snapshot);
        return array;

    }
    public static List<Bird2D> generateDynamic(int total_agents , int space_width , double velocity) {
        List<Bird2D> birds = new ArrayList<>();
        for( int i = 0 ; i < total_agents ; i++){
            double x_pos = Math.random() * space_width;
            double y_pos = Math.random() * space_width;

            Vector2D pos = new Vector2D(x_pos , y_pos);

            double x_vel = (Math.random() - 0.5) *2;
            double y_vel = (Math.random() - 0.5) *2;
            Vector2D vel = new Vector2D(x_vel , y_vel).normalize().mul(velocity);

            birds.add(new Bird2D(pos , vel));

        }



        return birds;
    }

}
