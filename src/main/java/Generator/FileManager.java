package Generator;


import Models.Bird2D;
import Models.Parameters;
import Models.Vector2D;
import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private final static String DIRECTORY = "./results";

    public static void main(String[] args) {

        Parameters parameters = Config.getParameters();
        if(parameters == null) return;
        List<Bird2D> birds = BirdGenerator.generateBirds(parameters.total_agents , parameters.space_width , parameters.velocity);
        String folder = createStaticInfo(parameters);
        JSONArray array = generateDynamicFromBirds(birds , null );
        saveSnapshots(array , folder);
    }


    //Returns folder
    public static String createStaticInfo(Parameters p) {
        File dir = new File(DIRECTORY );
        dir.mkdir();
        if(p.folder == null){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd--HH-mm-ss");
            LocalDateTime time = LocalDateTime.now();
            p.folder = dtf.format(time);
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("total_agents" , p.total_agents);
        jsonObject.put("space_width" , p.space_width);
        jsonObject.put("radius" , p.radius);
        jsonObject.put("theta_amp" , p.theta_amp);
        jsonObject.put("total_steps" , p.total_steps);
        jsonObject.put("velocity" , p.velocity);
        String filePath = DIRECTORY + "/" + p.folder + "/static.json";
        File dir2 = new File(DIRECTORY + "/" + p.folder);
        dir2.mkdir();
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
        return p.folder;
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
            bird_json.put("heading" , bird.vel.heading());
            this_array.put(bird_json);
        }
        snapshot.put("snapshot" , this_array);
        array.put(snapshot);
        return array;
    }





}
