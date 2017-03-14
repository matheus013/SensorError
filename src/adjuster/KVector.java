package adjuster;

import adjuster.model.Vector;
import read.UtilJson;
import util.Set.SetUtils;
import util.container.Pair;

import javax.json.JsonObject;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bash on 14/03/17.
 */
public class KVector {
    int K = 1;
    double T = 1.0;
    double confidence;
    ArrayList<JsonObject> population;
    ArrayList<Vector> vectors;

    public KVector() {}

    public KVector(ArrayList<JsonObject> population) {
        this.population = UtilJson.sort(population);
    }

    private KVector(ArrayList<JsonObject> population, int K, double T) {
        this.population = UtilJson.sort(population);
        this.K = K;
        this.T = T;
    }

    public void train(String var) {
        double lastFitness = 0.0;
        int i;
        for (i = K; lastFitness < 1.0 && i < population.size(); i++) {
            Pair<Double, ArrayList<Vector>> pair = fitness(i, var);
            double currentFitness = pair.getFirst();
            if (lastFitness == currentFitness && lastFitness != 0) {
                vectors = (ArrayList<Vector>) pair.getSecond().clone();
                break;
            }
            lastFitness = currentFitness;
        }
        K = i;
        confidence = lastFitness;
        System.out.println(K);
        System.out.println(confidence);
    }

    private Pair<Double, ArrayList<Vector>> fitness(int K, String var) {
        ArrayList<Vector> vectors = SetUtils.cut(K, population, var);
        int count = 0;
        for (int i = 0; i < population.size(); i++) {
            int aux = (int) Double.parseDouble(population.get(i).getString(var));
            if (vectors.get(i % K).dist(new Point(i, aux)) < 50.0) {
                count++;
            }
        }
        return Pair.create((double) count / population.size(), vectors);
    }

    public double test(Point p) {
        return vectors.get((int) p.getX() % K).dist(p);
    }
}
