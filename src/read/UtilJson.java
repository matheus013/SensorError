package read;

import javax.json.*;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

public class UtilJson {
    public static JsonArray getJsonArrayFromFile(String url) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(new FileReader(url));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonStructure jsonst = reader.read();
        return (JsonArray) jsonst;
    }

    public static JsonArray subsetByVar(JsonArray array, String var, String ref) {
        String str = "";
        for (JsonValue jsonValue : array) {
            if (((JsonObject) jsonValue).getString(var).equals(ref)) {
                str += ((JsonObject) jsonValue).toString() + ",";
            }
        }
        str = "[" + str.substring(0, str.length() - 1) + "]";

        return toJson(str);
    }

    public static ArrayList<JsonObject> toArrayList(JsonArray array) {
        ArrayList<JsonObject> objects = new ArrayList<>();
        for (JsonValue jsonValue : array) {
            objects.add((JsonObject) jsonValue);
        }
        return objects;
    }

    public static ArrayList<JsonObject> sort(ArrayList<JsonObject> array) {
//        for (int i = array.size() - 1; i >= 1; i--) {
//            for (int j = 0; j < i; j++) {
//                if (toMs(array.get(j).getString("date")) < toMs(array.get(j + 1).getString("date"))) {
//                    Collections.swap(array, j, j + 1);
//                }
//            }
//        }
        return array;
    }

    private static long toMs(String date) {
        return Instant.parse(date).toEpochMilli();
    }

    public static JsonArray toJson(String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        JsonReader reader = Json.createReader(stream);
        JsonStructure jsonst = reader.read();
        JsonArray a = (JsonArray) jsonst;
        return a;
    }
}
