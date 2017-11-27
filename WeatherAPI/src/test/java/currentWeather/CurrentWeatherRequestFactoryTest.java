package currentWeather;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import urlBuilder.URLBuilder;
import weatherRequest.WeatherRequest;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CurrentWeatherRequestFactoryTest {
    private CurrentWeatherRequestFactory currentWeatherRequestFactory;
    private URLBuilder urlBuilder;
    private CurrentWeatherReport correctCurrentWeatherReport;
    private CurrentWeatherReport actualCurrentWeatherReport;
    private WeatherRequest weatherRequest;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject requestJson;
    private JSONObject responseJson;
    private JSONParser parser;
    private FileReader fileReader;
    private String filename;


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
        urlBuilder = mock(URLBuilder.class);
        when(urlBuilder.buildURL(anyString(), anyString(), anyString(), anyString())).thenReturn("http://api.openweathermap.org/data/2.5/weather?q=Tallinn%2CEE&APPID=24f99e919834ab7ccbf49162e4fc38a4&units=metric");
        currentWeatherRequestFactory = new CurrentWeatherRequestFactory(urlBuilder);


        // Read the fake response from file because it's waay too long
        filename = "currentWeatherResponse.txt";
        parser = new JSONParser();
        fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(filename).getFile());
        responseJson = (JSONObject) parser.parse(fileReader);

        correctCurrentWeatherReport = new CurrentWeatherReport("Tallinn", "EE", 2, 59.44, 24.75);
        actualCurrentWeatherReport = currentWeatherRequestFactory.makeWeatherReportFromJsonResponse(responseJson);

    }

    @Test
    public void testIfUrlBuilderIsCalledWhenMakingARequest() {
        System.out.println(currentWeatherRequestFactory.makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(requestJson));
        verify(urlBuilder, times(1)).buildURL(anyString(), anyString(), anyString(), anyString());
    }


    @Test
    public void testIfJSONObjectBuilderMakesCorrectObject() {
        assertEquals(requestJson, currentWeatherRequestFactory.buildWeatherRequestAsJSONObjectFromParameters(city,countryCode, units));
    }

    @Test
    public void testIfBuilderMakesCorrectWeatherRequestFromJSONObject() {
        WeatherRequest weatherRequestFromJSON = currentWeatherRequestFactory.buildWeatherRequestFromJSON(requestJson);
        assertEquals(requestJson.get("city"), weatherRequestFromJSON.getCity());
        assertEquals(requestJson.get("countryCode"), weatherRequestFromJSON.getCountry());
        assertEquals(requestJson.get("units"), weatherRequestFromJSON.getUnit());
    }

    @Test
    public void testIfWeatherReportHasCorrectCity() {
        assertEquals(correctCurrentWeatherReport.getCity(), actualCurrentWeatherReport.getCity());
    }

    @Test
    public void testIfWeatherReportHasCorrectCountry() {
        assertEquals(correctCurrentWeatherReport.getCountry(), actualCurrentWeatherReport.getCountry());
    }

    @Test
    public void testIfWeatherReportHasCorrectTemp() {
        assertEquals(correctCurrentWeatherReport.getTemp(), actualCurrentWeatherReport.getTemp(), 0.01);
    }

    @Test
    public void testIfWeatherReportHasCorrectLatitude() {
        assertEquals(correctCurrentWeatherReport.getLatitude(), actualCurrentWeatherReport.getLatitude(),0.01);
    }

    @Test
    public void testIfWeatherReportHasCorrectLongitude() {
        assertEquals(correctCurrentWeatherReport.getLongitude(), actualCurrentWeatherReport.getLongitude(),0.01);
    }



}
