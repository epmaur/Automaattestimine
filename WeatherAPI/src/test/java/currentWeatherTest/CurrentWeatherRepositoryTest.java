package currentWeatherTest;

import currentWeather.CurrentWeatherReport;
import currentWeather.CurrentWeatherRepository;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

/**
 * Created by Epu on 28.09.2017.
 */
public class CurrentWeatherRepositoryTest {
    private WeatherRequest weatherRequest;
    private CurrentWeatherRepository currentWeatherRepository;
    private CurrentWeatherReport currentWeatherReport;
    private double longitudeOfTallinn;
    private double latitudeOfTallinn;

    @Before
    public void initObjects() throws IOException, ParseException {
        weatherRequest = new WeatherRequest("Tallinn","EE");
        currentWeatherRepository = new CurrentWeatherRepository();
        currentWeatherReport = currentWeatherRepository.makeJSONResponseIntoWeatherReport(weatherRequest);
        longitudeOfTallinn = 24.7535;
        latitudeOfTallinn = 59.437;
    }

    @Test
    public void testIfResponseCityMatchesRequestCity() {
        assertEquals(weatherRequest.getCity(), currentWeatherReport.getCity());
    }

    @Test
    public void testIfResponseCountryMatchesRequestCountry() {
        assertEquals(weatherRequest.getCountry(), currentWeatherReport.getCountry());
    }

    @Test
    public void testIfResponseContainsCoordinates() {
        assertNotEquals(null, currentWeatherReport.getLatitude());
        assertNotEquals(null, currentWeatherReport.getLongitude());
    }

    @Test
    public  void testIfResponseContainsTemperature() {
        assertNotEquals(null, currentWeatherReport.getTemp());
    }

}
