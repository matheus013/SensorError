package adjuster;

import java.util.HashMap;

import javax.json.JsonArray;

import read.UtilJson;
import util.math.MathJson;

public class Appraiser {
	static HashMap<String, State> meanStates;
	static HashMap<String, State> standardStates;

	private void refresh(JsonArray array, String node, String[] vars) {
		// TODO Auto-generated method stub
		JsonArray subset = UtilJson.subsetByVar(array, "nodeID", node);
		for (String var : vars) {
			double mean = MathJson.mean(subset, var);
			double standard = MathJson.standard(subset, var);
		}

	}
}
