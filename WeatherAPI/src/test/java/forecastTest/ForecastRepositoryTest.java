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


    @Before
    public void initObjects() throws IOException, ParseException {
        weatherRequest = new WeatherRequest("Tallinn","EE", "metric");
        forecastRepository = new ForecastRepository();
        forecastReport = forecastRepository.makeJSONResponseIntoForecastReport(weatherRequest);
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

    @Test
    public void testIfCoordinatesAreValid() {
        boolean latitudeIsValid = forecastReport.getLatitude() <= 90 && forecastReport.getLatitude() >= -90;
        boolean longitudeIsValid = forecastReport.getLongitude() <= 180 && forecastReport.getLongitude() >= -180;
        assertEquals(true, latitudeIsValid);
        assertEquals(true, longitudeIsValid);
    }

    @Test
    public void testIfTemperaturesAreValid() {

        boolean dayOneMinTempIsValid = forecastReport.getDayOne().getMinTemp() > -100 && forecastReport.getDayOne().getMinTemp() < 100;
        boolean dayOneMaxTempIsValid = forecastReport.getDayOne().getMaxTemp() > -100 && forecastReport.getDayOne().getMaxTemp() < 100;
        boolean dayTwoMinTempIsValid = forecastReport.getDayTwo().getMinTemp() > -100 && forecastReport.getDayTwo().getMinTemp() < 100;
        boolean dayTwoMaxTempIsValid = forecastReport.getDayTwo().getMaxTemp() > -100 && forecastReport.getDayTwo().getMaxTemp() < 100;
        boolean dayThreeMinTempIsValid = forecastReport.getDayThree().getMinTemp() > -100 && forecastReport.getDayThree().getMinTemp() < 100;
        boolean dayThreeMaxTempIsValid = forecastReport.getDayThree().getMaxTemp() > -100 && forecastReport.getDayThree().getMaxTemp() < 100;

        assertEquals(true, dayOneMinTempIsValid);
        assertEquals(true, dayOneMaxTempIsValid);
        assertEquals(true, dayTwoMinTempIsValid);
        assertEquals(true, dayTwoMaxTempIsValid);
        assertEquals(true, dayThreeMinTempIsValid);
        assertEquals(true, dayThreeMaxTempIsValid);
    }

}
