package adjuster;

import read.UtilJson;
import util.constants.Constants;
import util.container.Pair;
import util.log.Log;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public class Training {
    private double start = 0.0;
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
        Log.write(var,"log/g.plot");
        double coverage = 0.0;
        double lastFitness = 0.0;
        for (double i = start; lastFitness < 1.0; i += 0.1) {
            double currentFitness = fitness(i, var, node);
            Log.write(i + "  " + currentFitness,"log/g.plot");
            if (currentFitness == lastFitness)
                break;
            coverage = Math.max(coverage, currentFitness);
            lastFitness = currentFitness;
        }
        app.coefficients.put(Pair.create(node, var), coverage);
    }

    private double fitness(double coefficient, String var, String node) {
        JsonArray subset = UtilJson.subsetByVar(Constants.data, "nodeID", node);
        int b = 0, a = 0;
        for (JsonValue jsonValue : subset) {
            double value = Double.parseDouble(((JsonObject) jsonValue).getString(var));
            if (app.valid(value, node, var, coefficient)) {
                a++;
            }
            b++;
        }
        return (double) a / b;
    }

    public Appraiser getApp() {
        return app;
    }

}
