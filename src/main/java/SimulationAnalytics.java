import Generator.Config;
import Models.Parameters;
import com.sun.java.browser.plugin2.DOM;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class SimulationAnalytics {

    public static void main(String[] args){
        Parameters parameters = Config.getParameters();
        if(parameters == null)
            throw new RuntimeException("Invalid Parameters");


        if( parameters.runs ==null){
            Simulation.runSimulation(parameters);
            return;
        }

        if( parameters.folder == null)
            throw new RuntimeException("Invalid Parameters");

        List<Pair<Integer,Double>> paramList = new ArrayList<>();
        paramList.add(new Pair<>(40 , 8.0));
        paramList.add(new Pair<>(100, 12.64));
        paramList.add(new Pair<>(400 , 25.29));
        //paramList.add(new Pair<>(4000 , 80.0));
       // paramList.add(new Pair<>(10000 , 126.49));
        String folderName = parameters.folder;
        for (Pair<Integer,Double> p: paramList) {
            parameters.total_agents = p.getKey();
            parameters.space_width = p.getValue();
            parameters.theta_amp = 5.0;
            while(parameters.theta_amp <= 5.1) {
                parameters.runs = Math.abs(parameters.runs);
                for (int i = 0; i < parameters.runs; i++) {
                    parameters.folder = folderName + "_" + parameters.total_agents + "_" + String.format("%.2f",parameters.theta_amp) + "_" +i;
                    Simulation.runSimulation(parameters);
                }
                parameters.theta_amp += 0.2;
            }
        }

    }
}
