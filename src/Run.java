import adjuster.Appraiser;
import adjuster.Training;

/**
 * Created by matheus on 10/03/17.
 */
public class Run {
    String[] vars = {"sensirion_temp", "sensirion_hum", "intersema_press", "infrared_light", "light"};
    String[] nodes = {"11", "12"};
    Appraiser app;

    public void refresh() {
        Training training = new Training(nodes);
        for (String node : nodes) {
            for (String var : vars) {
                training.train(node, var);
            }
        }
        app = training.getApp();
    }

    public boolean rereading(double reading, String node, String var) {
        return app.valid(reading, node, var);
    }

    public Appraiser getApp() {
        return app;
    }
}
