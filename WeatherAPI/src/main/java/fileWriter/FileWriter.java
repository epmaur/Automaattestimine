package fileWriter;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {


    public void writeJsonToFile(JSONObject jsonObject, String filename) {
        try {
            Files.write(Paths.get(getClass().getClassLoader().getResource(filename).getPath()), jsonObject.toString().getBytes(), StandardOpenOption.WRITE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
