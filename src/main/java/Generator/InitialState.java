package Generator;


import org.json.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InitialState {
    private final static String DIRECTORY = "./results";

    public static void main(String[] args) {
        createStaticInfo(10 , 10 , 0.5 , 0.5 , null);
    }


    public static void createStaticInfo(int total_agents , int space_width , double radius , double theta_amp , String folder) {
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

    }

}
