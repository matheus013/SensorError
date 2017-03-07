package read;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;

public class UtilJson {
	public static JsonArray getJsonArrayFromFile(String url) {
		JsonReader reader = null;
		try {
			reader = Json.createReader(new FileReader(url));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonStructure jsonst = reader.read();
		JsonArray a = (JsonArray) jsonst;
		return a;
	}

	public static JsonArray subsetByVar(JsonArray array, String var, String ref) {
		String str = "";
		for (JsonValue jsonValue : array) {
			if (((JsonObject) jsonValue).getString(var).equals(ref)) {
				str += ((JsonObject) jsonValue).toString() + ",";
			}
		}
		str = "[" + str.substring(0, str.length() - 1) + "]";

		InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
		JsonReader reader = Json.createReader(stream);
		JsonStructure jsonst = reader.read();
		JsonArray a = (JsonArray) jsonst;
		return a;
	}
}
