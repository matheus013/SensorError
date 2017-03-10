package util.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

/**
 * Created by matheus on 10/03/17.
 */
public class Log {

    private static String name() {
        return LocalDate.now().toString();
    }

    public static void write(String log) {
        log += "\n";
        String name = "log/"+ name() + ".log";
        File f = new File(name);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(Paths.get(name), log.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
