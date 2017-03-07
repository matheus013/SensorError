import javax.json.JsonArray;

import read.UtilJson;
import util.math.MathJson;

public class Main {
	public static void main(String[] args) {
		JsonArray a = UtilJson.getJsonArrayFromFile("/home/matheus/workspace/SensorError/data/dados.json");
		System.out.println(MathJson.mean(a, "sensirion_temp"));
		System.out.println(MathJson.min(a, "sensirion_temp"));
		System.out.println(MathJson.max(a, "sensirion_temp"));

		System.out.println(a.size());
		System.out.println(UtilJson.subsetByVar(a, "nodeID", "12").size());
		System.out.println(UtilJson.subsetByVar(a, "nodeID", "11").size());

		System.out.println(MathJson.mean(UtilJson.subsetByVar(a, "nodeID", "11"), "sensirion_temp"));
		System.out.println(MathJson.mean(UtilJson.subsetByVar(a, "nodeID", "12"), "sensirion_temp"));

		System.out.println(MathJson.standard(a, "sensirion_temp") * 3);
		System.out.println(MathJson.standard(a, "sensirion_temp") * 2);
	}
}
