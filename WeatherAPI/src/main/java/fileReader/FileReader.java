package fileReader;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import java.io.IOException;

public class FileReader {



    public WeatherRequest readInputFromFile(String filename) {
        JSONParser parser = new JSONParser();
        WeatherRequest weatherRequest = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(filename).getFile());
            JSONObject inputFile = (JSONObject) parser.parse(fileReader);
            String city = (String) inputFile.get("city");
            String countryCode = (String) inputFile.get("countryCode");
            String units = (String) inputFile.get("units");
            weatherRequest = new WeatherRequest(city, countryCode, units);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return weatherRequest;

    }

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        String filename = "input.txt";
        System.out.println(fileReader.readInputFromFile(filename));
    }



}
