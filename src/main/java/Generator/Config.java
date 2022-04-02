package Generator;

import Models.Bird2D;
import Models.Parameters;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
    private static final String CONFIG_FILEPATH = "./config.json";
    private static final String[] REQUIRED_PARAMETERS = { "total_agents" , "total_steps" , "space_width" , "velocity" , "radius" , "theta_amp" } ;
    public static Parameters getParameters(){
        File file = new File(CONFIG_FILEPATH);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject json = new JSONObject(content);
            boolean error = false;
            for (String param: REQUIRED_PARAMETERS) {
                if(!json.has(param)){
                    error = true;
                    System.out.println("Error: missing param " + param);
                }
            }
            if(error) return null;
            Parameters parameters = new Parameters(
                    json.getInt("total_agents"),
                    json.getInt("total_steps"),
                    json.getInt("space_width"),
                    json.getDouble("velocity"),
                    json.getDouble("radius"),
                    json.getDouble("theta_amp"),
                    (json.has("folder")? json.getString("folder"):null)
            );
            System.out.println(parameters);
            return parameters;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
