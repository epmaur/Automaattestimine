package forecastTest;

import forecast.ForecastReport;
import forecast.ForecastRepository;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Epu on 28.09.2017.
 */
public class ForecastRepositoryTest {
    private WeatherRequest weatherRequest;
    private ForecastRepository forecastRepository;
    private ForecastReport forecastReport;
    private double longitudeOfTallinn;
    private double latitudeOfTallinn;

    @Before
    public void initObjects() throws IOException, ParseException {
        weatherRequest = new WeatherRequest("Tallinn","EE");
        forecastRepository = new ForecastRepository();
        forecastReport = forecastRepository.makeJSONResponseIntoForecastReport(weatherRequest);
        longitudeOfTallinn = 24.7535;
        latitudeOfTallinn = 59.437;
    }

    @Test
    public void testIfResponseCityMatchesRequestCity() {
        assertEquals(weatherRequest.getCity(), forecastReport.getCity());
    }

    @Test
    public void testIfResponseCountryMatchesRequeestCountry() {
        assertEquals(weatherRequest.getCountry(), forecastReport.getCountry());
    }

    @Test
    public void testIfResponseContainsCoordinates() {
        assertNotEquals(null, forecastReport.getLatitude());
        assertNotEquals(null, forecastReport.getLongitude());
    }

    @Test
    public void testIfResponseContainsThreeDayForecast() {
        assertNotEquals(null, forecastReport.getDayOne());
        assertNotEquals(null, forecastReport.getDayTwo());
        assertNotEquals(null, forecastReport.getDayThree());
    }

    @Test
    public void testIfResponseForecastDaysContainMaxAndMinTemp() {
        assertNotEquals(null, forecastReport.getDayOne().getMinTemp());
        assertNotEquals(null, forecastReport.getDayOne().getMaxTemp());
        assertNotEquals(null, forecastReport.getDayTwo().getMinTemp());
        assertNotEquals(null, forecastReport.getDayTwo().getMaxTemp());
        assertNotEquals(null, forecastReport.getDayThree().getMinTemp());
        assertNotEquals(null, forecastReport.getDayThree().getMaxTemp());
    }

}
