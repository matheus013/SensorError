import adjuster.Regression;

public class Main {
    public static void main(String[] args) {
        //Method of separation
//        Run run = new Run();
//        run.refresh();
        //Nonlinear regression methods
//        JsonArray jsonArray = UtilJson.getJsonArrayFromFile("/home/bash/SensorError/data/dados.json");
//        ArrayList<JsonObject> array = UtilJson.toArrayList(UtilJson.subsetByVar(jsonArray, "nodeID", "11"));
//        KVector v = new KVector(array);
//        v.train("sensirion_temp");
        Regression r = new Regression();
        r.run("/home/bash/SensorError/data/dados.json");
//        ArrayList<Vector> v = r.getVectors().get(Pair.create("11", "sensirion_temp")).getVectors();
//        for (Vector vector : v) {
//            System.out.println(vector);
//        }
    }

}
