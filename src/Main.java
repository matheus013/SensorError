import adjuster.KVector;
import read.UtilJson;

import javax.json.JsonArray;

public class Main {
    public static void main(String[] args) {
//        Run run = new Run();
//        run.refresh();
        JsonArray array = UtilJson.getJsonArrayFromFile(
                "/home/bash/SensorError/data/dados.json");
        KVector k = new KVector(UtilJson.toArrayList(UtilJson.subsetByVar(array, "nodeID", "12")));
        k.train("sensirion_hum");
    }
}
