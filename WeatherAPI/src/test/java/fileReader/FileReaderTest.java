package fileReader;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private FileReader fileReader;
    private String filename;
    private JSONObject fileContent;
    private String city;
    private String countryCode;
    private String units;

    @Before
    public void initObjects() {
        fileReader = new FileReader();
        filename = "inputTest.txt";
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        fileContent = fileReader.readInputFromFile(filename);
    }

    @Test
    public void testIfWeatherRequestCityNameMatches() {
        assertEquals(city, fileContent.get("city"));
    }

    @Test
    public void testIfWeatherRequestCountryMatches() {
        assertEquals(countryCode, fileContent.get("countryCode"));
    }

    @Test
    public void testIfWeatherRequestUnitMatches() {
        assertEquals(units, fileContent.get("units"));
    }

}
