import javax.json.JsonObject;
import javax.json.JsonValue;

import adjuster.Appraiser;
import util.constants.Constants;

public class Main {
	public static void main(String[] args) {
		int a = 0, b = 0;
		Appraiser app = new Appraiser();
		app.refresh(Constants.data, "11", Constants.vars);
		for (int i = 0; i <= 500; i += 5) {
			app.coefficient = (double) i / 10.0;
			for (JsonValue jsonValue : Constants.data) {
				double t = Double.parseDouble(((JsonObject) jsonValue).getString("sensirion_temp"));
				if (app.valid(t, "11", "sensirion_temp")) {
					a++;
				}
				b++;
			}
			System.out.println(a);
			System.out.println("cof:= " + app.coefficient + " p:= " + ((double) a / b));
		}

	}
}
