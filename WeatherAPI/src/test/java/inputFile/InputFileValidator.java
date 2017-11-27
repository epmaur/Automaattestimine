package inputFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InputFileValidator {
    private FileReader fileReader;
    private String filename;
    private JSONArray fileContent;
    JSONParser parser = new JSONParser();

    @Before
    public void initObjects() {
        filename = "input.txt";
        try {
            fileReader = new FileReader(getClass().getClassLoader().getResource(filename).getFile());
            fileContent = (JSONArray) parser.parse(fileReader);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIfRequestObjectsHaveCity() {
        boolean allObjectsHaveCity = true;
        for (int i = 0; i < fileContent.size(); i++) {
            JSONObject request = (JSONObject) fileContent.get(i);
            String city = (String) request.get("city");
            if (city == null) {
                allObjectsHaveCity = false;
            }
        }
        assertEquals(true, allObjectsHaveCity);
    }

    @Test
    public void testIfRequestObjectsHaveCountryCode() {
        boolean allObjectsHaveCountryCode = true;
        for (int i = 0; i < fileContent.size(); i++) {
            JSONObject request = (JSONObject) fileContent.get(i);
            String city = (String) request.get("countryCode");
            if (city == null) {
                allObjectsHaveCountryCode = false;
            }
        }
        assertEquals(true, allObjectsHaveCountryCode);
    }

    @Test
    public void testIfRequestObjectsHaveUnits() {
        boolean allObjectsHaveUnits = true;
        for (int i = 0; i < fileContent.size(); i++) {
            JSONObject request = (JSONObject) fileContent.get(i);
            String city = (String) request.get("units");
            if (city == null) {
                allObjectsHaveUnits = false;
            }
        }
        assertEquals(true, allObjectsHaveUnits);
    }



}
