package forecast;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ForecastOnlineResponseTest {
    private ForecastWeatherRequestFactory forecastWeatherRequestFactory;
    private ForecastReport forecastReport;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject jsonObject;

    @Before
    public void initObjects() throws IOException, ParseException {
        forecastWeatherRequestFactory = new ForecastWeatherRequestFactory();
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";

        jsonObject = new JSONObject();
        jsonObject.put("city", city);
        jsonObject.put("countryCode", countryCode);
        jsonObject.put("units", units);
        forecastReport = forecastWeatherRequestFactory.makeWeatherRequestAndReturnResponseAsForecastReport(jsonObject);
    }

    @Test
    public void testIfResponseCityMatchesRequestCity() {
        assertEquals(jsonObject.get("city"), forecastReport.getCity());
    }


    @Test
    public void testIfResponseCountryMatchesRequestCountry() {
        assertEquals(jsonObject.get("countryCode"), forecastReport.getCountry());
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
    public void testIfResponseForecastDayOneContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getDayOne().getMaxTemp());
    }

    @Test
    public void testIfResponseForecastDayOneContainsMinTemp() {
        assertNotEquals(null, forecastReport.getDayOne().getMinTemp());
    }

    @Test
    public void testIfResponseForecastDayTwoContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getDayTwo().getMaxTemp());
    }
    @Test
    public void testIfResponseForecastDayTwoContainsMinTemp() {
        assertNotEquals(null, forecastReport.getDayTwo().getMinTemp());
    }

    @Test
    public void testIfResponseForecastDayThreeContainsMaxTemp() {
        assertNotEquals(null, forecastReport.getDayThree().getMaxTemp());
    }
    @Test
    public void testIfResponseForecastDayThreeContainsMinTemp() {
        assertNotEquals(null, forecastReport.getDayThree().getMinTemp());
    }


    @Test
    public void testIfCoordinatesAreValid() {
        boolean latitudeIsValid = forecastReport.getLatitude() <= 90 && forecastReport.getLatitude() >= -90;
        boolean longitudeIsValid = forecastReport.getLongitude() <= 180 && forecastReport.getLongitude() >= -180;
        assertEquals(true, latitudeIsValid);
        assertEquals(true, longitudeIsValid);
    }

    @Test
    public void testIfDayOneMaxTempIsValid() {
        boolean dayOneMaxTempIsValid = forecastReport.getDayOne().getMaxTemp() > -100 && forecastReport.getDayOne().getMaxTemp() < 100;
        assertEquals(true, dayOneMaxTempIsValid);

    }

    @Test
    public void testIfDayOneMinTempIsValid() {
        boolean dayOneMinTempIsValid = forecastReport.getDayOne().getMinTemp() > -100 && forecastReport.getDayOne().getMinTemp() < 100;
        assertEquals(true, dayOneMinTempIsValid);

    }

    @Test
    public void testIfDayTwoMaxTempIsValid() {
        boolean dayTwoMaxTempIsValid = forecastReport.getDayTwo().getMaxTemp() > -100 && forecastReport.getDayTwo().getMaxTemp() < 100;
        assertEquals(true, dayTwoMaxTempIsValid);
    }

    @Test
    public void testIfDayTwoMinTempIsValid() {
        boolean dayTwoMinTempIsValid = forecastReport.getDayTwo().getMinTemp() > -100 && forecastReport.getDayTwo().getMinTemp() < 100;
        assertEquals(true, dayTwoMinTempIsValid);
    }

    @Test
    public void testIfDayThreeMaxTempIsValid() {
        boolean dayThreeMaxTempIsValid = forecastReport.getDayThree().getMaxTemp() > -100 && forecastReport.getDayThree().getMaxTemp() < 100;
        assertEquals(true, dayThreeMaxTempIsValid);
    }

    @Test
    public void testIfDayThreeMinTempIsValid() {
        boolean dayThreeMinTempIsValid = forecastReport.getDayThree().getMinTemp() > -100 && forecastReport.getDayThree().getMinTemp() < 100;
        assertEquals(true, dayThreeMinTempIsValid);
    }

}
