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
    private WeatherRequest weatherRequest;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject requestJson;


    @Before
    public void initObjects() throws IOException, ParseException {
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        requestJson = new JSONObject();
        requestJson.put("city", city);
        requestJson.put("countryCode", countryCode);
        requestJson.put("units", units);
        weatherRequest = new WeatherRequest(city, countryCode, units);
        currentWeatherFactory = new CurrentWeatherFactory();

        currentWeatherReport = currentWeatherFactory.makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(requestJson);
    }

    @Test
    public void testIfBuilderMakesCorrectURL() {
        String correctUrl = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn%2CEE&APPID=24f99e919834ab7ccbf49162e4fc38a4&units=metric";
        assertEquals(correctUrl, currentWeatherFactory.buildCurrentWeatherURL(weatherRequest));
    }

    @Test
    public void testIfResponseReportCityMatchesRequestCity() {
        assertEquals(requestJson.get("city"), currentWeatherReport.getCity());
    }

    @Test
    public void testIfResponseReportCountryMatchesRequestCountry() {
        assertEquals(requestJson.get("countryCode"), currentWeatherReport.getCountry());
    }

    @Test
    public void testIfResponseReportContainsCoordinates() {
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

    @Test
    public void testIfJSONObjectBuilderMakesCorrectObject() {
        assertEquals(requestJson, currentWeatherFactory.buildWeatherRequestAsJSONObjectFromParameters(city,countryCode, units));
    }

    @Test
    public void testIfBuilderMakesCorrectWeatherRequestFromJSONObject() {
        WeatherRequest weatherRequestFromJSON = currentWeatherFactory.buildWeatherRequestFromJSON(requestJson);
        assertEquals(requestJson.get("city"), weatherRequestFromJSON.getCity());
        assertEquals(requestJson.get("countryCode"), weatherRequestFromJSON.getCountry());
        assertEquals(requestJson.get("units"), weatherRequestFromJSON.getUnit());
    }



}
