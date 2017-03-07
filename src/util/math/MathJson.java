package util.math;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class MathJson {
	public static double mean(JsonArray array, String var) {
		double sum = 0;
		for (JsonValue jsonValue : array) {
			sum += Double.parseDouble(((JsonObject) jsonValue).getString(var));
		}

		return sum / array.size();

	}

	public static double max(JsonArray array, String var) {
		double result = Double.MIN_VALUE;
		for (JsonValue jsonValue : array) {
			double auxNumber = Double.parseDouble(((JsonObject) jsonValue).getString(var));
			result = Math.max(auxNumber, result);
		}

		return result;

	}

	public static double min(JsonArray array, String var) {
		double result = Double.MAX_VALUE;
		for (JsonValue jsonValue : array) {
			double auxNumber = Double.parseDouble(((JsonObject) jsonValue).getString(var));
			result = Math.min(auxNumber, result);
		}

		return result;

	}

	public static double standard(JsonArray array, String var) {
		double m = mean(array, var);
		double sum = 0;
		for (JsonValue jsonValue : array) {
			double i = Double.parseDouble(((JsonObject) jsonValue).getString(var));
			sum += Math.pow((i - m), 2);
		}
		return Math.sqrt(sum / (array.size() - 1));

	}
}
