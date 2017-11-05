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
    private JSONObject responseInJson;
    private String filename;
    private String city;
    private String raining;


    public void initObjects() {
        fileWriter = new FileWriter();
        responseInJson = new JSONObject();
        responseInJson.put("city", "Tallinn");
        responseInJson.put("raining?", "always");
        filename = "outputTest.txt";
    }


    public void writeObjectToFile() {
        fileWriter.writeJsonToFile(responseInJson, filename);
    }


    public void readOutputFile() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        java.io.FileReader fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(filename).getFile());
        JSONObject inputFile = (JSONObject) parser.parse(fileReader);
        city = (String) inputFile.get("city");
        raining = (String) inputFile.get("raining?");
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
    public void testIfFileContainsRainingKey() {
        assertNotEquals(null, raining);
    }


    @Test
    public void testIfFileContainsCorrectCity() {
        assertEquals(responseInJson.get("city"), city);
    }

    @Test
    public void testIfFileContainsCorrectResponseToRainingQuestion() {
        assertEquals(responseInJson.get("raining?"), raining);
    }

}
