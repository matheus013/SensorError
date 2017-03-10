package adjuster;

import javax.json.JsonObject;
import javax.json.JsonValue;

import util.constants.Constants;

public class Training {
	private double start = 1.0;
	private double end = 15.0;
	private Appraiser app;

	public Training(String node) {
		app = new Appraiser();
		app.refresh(Constants.data, node, Constants.vars);
	}

	public Training(String[] nodes) {
		app = new Appraiser();
		for (String node : nodes) {
			app.refresh(Constants.data, node, Constants.vars);
		}
	}

	public void train(String node, String var) {
		double coverage = 0.0;
		double lastFitness = 0.0;
		for (double i = start; lastFitness < 1.0; i += 0.25) {
			double currentFitness = fitness(i, var, node);
			if (currentFitness == lastFitness)
				break;
			coverage = Math.max(coverage, currentFitness);
			lastFitness = currentFitness;
		}
		app.coefficients.put(node, coverage);
	}

	private double fitness(double coefficient, String var, String node) {

		int b = 0, a = 0;
		for (JsonValue jsonValue : Constants.data) {
			double value = Double.parseDouble(((JsonObject) jsonValue).getString(var));
			if (app.valid(value, node, var, coefficient)) {
				a++;
			}
			b++;
		}
		System.out.println(a);
		System.out.println("K:= " + coefficient + " p:= " + ((double) a / b));

		return (double) a / b;
	}

	public Appraiser getApp() {
		return app;
	}

}
