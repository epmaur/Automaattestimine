package currentWeather;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CurrentWeatherFactoryTest {
    private CurrentWeatherFactory currentWeatherFactory;
    private CurrentWeatherReport currentWeatherReport;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject jsonObject;


    @Before
    public void initObjects() throws IOException, ParseException {
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        jsonObject = new JSONObject();
        jsonObject.put("city", city);
        jsonObject.put("countryCode", countryCode);
        jsonObject.put("units", units);
        currentWeatherFactory = new CurrentWeatherFactory();
        JSONObject response = currentWeatherFactory.makeCurrentWeatherRequestAndReturnResponseInJson(jsonObject);
        currentWeatherReport = currentWeatherFactory.makeJSONResponseIntoWeatherReport(response);
    }

    @Test
    public void testIfJSONObjectBuilderMakesCorrectObject() {
        assertEquals(jsonObject, currentWeatherFactory.buildJSONObjectFromParameters(city,countryCode, units));
    }

    @Test
    public void testIfBuilderMakesCorrectWeatherRequestFromJSONObject() {
        WeatherRequest weatherRequestFromJSON = currentWeatherFactory.buildWeatherRequestFromJSON(jsonObject);
        assertEquals(jsonObject.get("city"), weatherRequestFromJSON.getCity());
        assertEquals(jsonObject.get("countryCode"), weatherRequestFromJSON.getCountry());
        assertEquals(jsonObject.get("units"), weatherRequestFromJSON.getUnit());
    }


    @Test
    public void testIfResponseCityMatchesRequestCity() {
        assertEquals(jsonObject.get("city"), currentWeatherReport.getCity());
    }

    @Test
    public void testIfResponseCountryMatchesRequestCountry() {
        assertEquals(jsonObject.get("countryCode"), currentWeatherReport.getCountry());
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

    @Test
    public void testIfCoordinatesAreValid() {
        boolean latitudeIsValid = currentWeatherReport.getLatitude() <= 90 && currentWeatherReport.getLatitude() >= -90;
        boolean longitudeIsValid = currentWeatherReport.getLongitude() <= 180 && currentWeatherReport.getLongitude() >= -180;
        assertEquals(true, latitudeIsValid);
        assertEquals(true, longitudeIsValid);
    }

    @Test
    public void testIfTemperatureIsValid() {
        boolean temperatureIsValid = currentWeatherReport.getTemp() > -100 && currentWeatherReport.getTemp() < 100;
        assertEquals(true, temperatureIsValid);
    }


}
