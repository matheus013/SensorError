package adjuster;

import java.util.ArrayList;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import read.UtilJson;
import util.math.MathJson;

public class Appraiser {
	public double coefficient;
	private ArrayList<String> data;
	private HashMap<String, Double> errors;

	public Appraiser() {
		data = new ArrayList<>();
		errors = new HashMap<>();
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
			if (!errors.containsKey(node)) {
				errors.put(node, (value - mean));
			} else {
				errors.replace(node, (value - mean));
			}
		}

		return value >= (mean - coefficient * standard) && value <= (mean + coefficient * standard);
	}

	public HashMap<String, Double> getErrors() {
		return errors;
	}

}
