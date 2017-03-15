package util.Set;

import adjuster.model.Vector;

import javax.json.JsonObject;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by bash on 14/03/17.
 */
public class SetUtils {

    public static ArrayList<Vector> cut(int K, ArrayList<JsonObject> objects, String var) {
        int v = objects.size() / K;
        ArrayList<Vector> pieces = new ArrayList<>();
        for (int i = 0; i < objects.size() - v; i += v) {
            pieces.add(new Vector(new Point(i, (int) Double.parseDouble(objects.get(i).getString(var))),
                    new Point(i + v, (int) Double.parseDouble(objects.get(i + v).getString(var)))));
        }
        if (K == 1) {
            pieces.add(new Vector(new Point(0, (int) Double.parseDouble(objects.get(0).getString(var))),
                    new Point(v, (int) Double.parseDouble(objects.get(v - 1).getString(var)))));
        }
        return pieces;
    }

}
