package adjuster;

import read.UtilJson;
import util.container.Pair;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by bash on 14/03/17.
 */
public class Regression {
    final String[] vars = {"sensirion_temp", "sensirion_hum", "intersema_press", "infrared_light", "light"};
    final String[] nodes = {"11", "12"};
    int days = 0;
    //key format node var
    HashMap<Pair<String, String>, KVector> vectors;

    public Regression() {
        vectors = new HashMap<>();
    }

    public void run(String baseFile) {
        JsonArray jsonArray = UtilJson.getJsonArrayFromFile(baseFile);
        for (String node : nodes) {
            for (String var : vars) {
                ArrayList<JsonObject> array = UtilJson.toArrayList(UtilJson.subsetByVar(jsonArray, "nodeID", node));
                if (!array.isEmpty()) {
                    KVector k = new KVector(array);
                    //Generate K straight to represent sample
                    k.train(var);
                    vectors.put(Pair.create(node, var), k);
                }
            }
        }
    }

    public HashMap<Pair<String, String>, KVector> getVectors() {
        return vectors;
    }
}
