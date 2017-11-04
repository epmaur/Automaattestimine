package FileWriter;

import fileWriter.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileWriterTest {
    private FileWriter fileWriter;
    private JSONObject weatherReportInJson;
    private String filename;
    private String city;
    private String countryCode;
    private String unit;


    public void initObjects() {
        fileWriter = new FileWriter();
        weatherReportInJson = new JSONObject();
        weatherReportInJson.put("city", "Tallinn");
        weatherReportInJson.put("country", "EE");
        weatherReportInJson.put("unit", "metric");
        filename = "outputTest.txt";
    }


    public void writeObjectToFile() {
        fileWriter.writeJsonToFile(weatherReportInJson, filename);
    }


    public void readOutputFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        java.io.FileReader fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(filename).getFile());
        JSONObject inputFile = (JSONObject) parser.parse(fileReader);
        city = (String) inputFile.get("city");
        countryCode = (String) inputFile.get("country");
        unit = (String) inputFile.get("unit");
    }

    @Before
    public void setUp() throws IOException, ParseException {
        initObjects();
        writeObjectToFile();
        readOutputFile();
    }

    @Test
    public void testIfFileContainsCityKey() {
        assertNotEquals(null, city);
    }

    @Test
    public void testIfFileContainsCountryCodeKey() {
        assertNotEquals(null, countryCode);
    }

    @Test
    public void testIfFileContainsUnitKey() {
        assertNotEquals(null, unit);
    }


    @Test
    public void testIfFileContainsCorrectCity() {
        assertEquals(weatherReportInJson.get("city"), city);
    }

    @Test
    public void testIfFileContainsCorrectCountryCode() {
        assertEquals(weatherReportInJson.get("country"), countryCode);
    }

    @Test
    public void testIfFileContainsCorrectUnit() {
        assertEquals(weatherReportInJson.get("unit"), unit);
    }
}
