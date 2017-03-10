package adjuster;

import read.UtilJson;
import util.container.Pair;
import util.log.Log;
import util.math.MathJson;

import javax.json.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Appraiser {
    protected HashMap<Pair<String, String>, Double> coefficients;
    private ArrayList<String> data;
    private HashMap<Pair<String, String>, Double> errors;

    public Appraiser() {
        data = new ArrayList<>();
        errors = new HashMap<>();
        coefficients = new HashMap<>();
    }

    public void refresh(JsonArray array, String node, String[] vars) {
        JsonArray subset = UtilJson.subsetByVar(array, "nodeID", node);
        JsonObjectBuilder a = Json.createObjectBuilder();
        for (String var : vars) {

            double mean = MathJson.mean(subset, var);
            double standard = MathJson.standard(subset, var);
            a.add(var, Json.createObjectBuilder().add("mean", mean).add("standard", standard));
        }
        data.add(Json.createObjectBuilder().add("data", a.build()).add("node", node).build().toString());

    }

    public JsonArray get() {
        String str = "";
        for (String string : data) {
            str += string + ",";
        }
        str = "[" + str.substring(0, str.length() - 1) + "]";

        return UtilJson.toJson(str);
    }

    public boolean valid(double value, String node, String var) {
        return valid(value, node, var, coefficients.get(Pair.create(node, var)));
    }

    protected boolean valid(double value, String node, String var, double coefficient) {
        // refresh(Constants.data, node, Constants.vars);
        double mean = 0, standard = 0;
        JsonArray array = get();
        for (JsonValue jsonValue : array) {
            if (((JsonObject) jsonValue).getString("node").equals(node)) {
                JsonObject object = ((JsonObject) jsonValue).getJsonObject("data").getJsonObject(var);
                mean = object.getJsonNumber("mean").doubleValue();
                standard = object.getJsonNumber("standard").doubleValue();
                break;
            }
        }
        if (!(value >= (mean - coefficient * standard) && value <= (mean + coefficient * standard))) {
            Log.write("Unexpected reading on node " + node + ". value:= " + ((double) Math.round(value * 100) / 100) + ", at variable " + var);
            if (!errors.containsKey(node)) {
                errors.put(Pair.create(node, var), (value - mean));
            } else {
                errors.replace(Pair.create(node, var), (value - mean));
            }
        }

        return value >= (mean - coefficient * standard) && value <= (mean + coefficient * standard);
    }

    public HashMap<Pair<String, String>, Double> getErrors() {
        return errors;
    }

}
