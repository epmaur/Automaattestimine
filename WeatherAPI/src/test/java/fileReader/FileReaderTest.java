package fileReader;

import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    private FileReader fileReader;
    private String filename;
    private WeatherRequest weatherRequest;
    private WeatherRequest weatherRequestFromFile;

    @Before
    public void initObjects() {
        fileReader = new FileReader();
        filename = "inputTest.txt";
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        weatherRequestFromFile = fileReader.readInputFromFile(filename);
    }

    @Test
    public void testIfWeatherRequestCityNameMatches() {
        assertEquals(weatherRequest.getCity(), weatherRequestFromFile.getCity());
    }

    @Test
    public void testIfWeatherRequestCountryMatches() {
        assertEquals(weatherRequest.getCountry(), weatherRequestFromFile.getCountry());
    }

    @Test
    public void testIfWeatherRequestUnitMatches() {
        assertEquals(weatherRequest.getUnit(), weatherRequestFromFile.getUnit());
    }

}
