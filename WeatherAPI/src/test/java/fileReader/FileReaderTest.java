package fileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private FileReader fileReader;
    private String filename;
    private JSONArray fileContent;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject fileContentObject;

    @Before
    public void initObjects() {
        fileReader = new FileReader();
        filename = "inputTest.txt";
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        fileContent = fileReader.readInputFromFile(filename);
        System.out.println(fileContent);
        fileContentObject = (JSONObject) fileContent.get(0);
    }

    @Test
    public void testIfWeatherRequestCityNameMatches() {
        assertEquals(city, fileContentObject.get("city"));
    }

    @Test
    public void testIfWeatherRequestCountryMatches() { assertEquals(countryCode, fileContentObject.get("countryCode")); }

    @Test
    public void testIfWeatherRequestUnitMatches() {
        assertEquals(units, fileContentObject.get("units"));
    }

}
