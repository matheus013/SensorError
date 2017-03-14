package util.constants;

import javax.json.JsonArray;

import read.UtilJson;

public class Constants {
	public static final String[] vars = { "sensirion_temp", "sensirion_hum", "intersema_press", "infrared_light",
			"light" };
	public static final JsonArray data = UtilJson
			.getJsonArrayFromFile("/home/bash/SensorError/data/dados.json");
}
