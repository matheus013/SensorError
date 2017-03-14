import adjuster.Regression;

public class Main {
    public static void main(String[] args) {
        //Method of separation
        Run run = new Run();
        run.refresh();
        //Nonlinear regression methods
        Regression r = new Regression();
        r.run("/home/bash/SensorError/data/dados.json");
    }
}
