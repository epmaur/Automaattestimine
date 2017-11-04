package fileWriter;

import org.json.simple.JSONObject;
import weatherRequest.WeatherRequest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("cihuiiy", "Tallinn");
        String filename = "output.txt";
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeJsonToFile(object, filename);
    }
}
