package forecast;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import urlBuilder.URLBuilder;
import weatherRequest.WeatherRequest;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ForecastWeatherRequestFactoryTest {
    private ForecastWeatherRequestFactory forecastWeatherRequestFactory;
    private URLBuilder urlBuilder;
    private ForecastReport correctForecastReport;
    private ForecastReport actualForecastReport;
    private WeatherRequest weatherRequest;
    private String city;
    private String countryCode;
    private String units;
    private JSONObject requestJson;

    private ForecastOneDayReport dayOne;
    private ForecastOneDayReport dayTwo;
    private ForecastOneDayReport dayThree;

    private String filename;
    private JSONParser parser;
    private FileReader fileReader;
    private JSONObject responseJson;



    @Before
    public void initObjects() throws IOException, ParseException, org.json.simple.parser.ParseException {
        urlBuilder = mock(URLBuilder.class);
        when(urlBuilder.buildURL(anyString(),anyString(),anyString(),anyString())).thenReturn("http://api.openweathermap.org/data/2.5/forecast?q=Tallinn%2CEE&APPID=24f99e919834ab7ccbf49162e4fc38a4&units=metric");
        forecastWeatherRequestFactory = new ForecastWeatherRequestFactory(urlBuilder);
        city = "Tallinn";
        countryCode = "EE";
        units = "metric";
        weatherRequest = new WeatherRequest(city,countryCode, units);
        requestJson = new JSONObject();
        requestJson.put("city", city);
        requestJson.put("countryCode", countryCode);
        requestJson.put("units", units);

        dayOne = new ForecastOneDayReport(4.85, 1.28);
        dayTwo = new ForecastOneDayReport(2.38, 0.88);
        dayThree = new ForecastOneDayReport(1.93, -0.4);

        correctForecastReport = new ForecastReport("Tallinn", "EE", 24.7535, 59.437, dayOne, dayTwo, dayThree);


        // Read the fake response from file because this one is ever longer
        filename = "forecastResponse.txt";
        parser = new JSONParser();
        fileReader = new java.io.FileReader(getClass().getClassLoader().getResource(filename).getFile());
        responseJson = (JSONObject) parser.parse(fileReader);

        actualForecastReport = forecastWeatherRequestFactory.makeForecastReportFromJsonResponse(responseJson);
    }

    @Test
    public void testIfURLBuilderIsCalledWhenMakingARequest() {
        forecastWeatherRequestFactory.makeWeatherRequestAndReturnResponseAsForecastReport(requestJson);
        verify(urlBuilder, times(1)).buildURL(anyString(), anyString(), anyString(), anyString());
    }


    @Test
    public void testIfJSONObjectBuilderMakesCorrectObject() {
        assertEquals(requestJson, forecastWeatherRequestFactory.buildJSONObjectFromParameters(city,countryCode, units));
    }

    @Test
    public void testIfBuilderMakesCorrectWeatherRequestFromJSONObject() {
        WeatherRequest weatherRequestFromJSON = forecastWeatherRequestFactory.buildWeatherRequestFromJSON(requestJson);
        assertEquals(requestJson.get("city"), weatherRequestFromJSON.getCity());
        assertEquals(requestJson.get("countryCode"), weatherRequestFromJSON.getCountry());
        assertEquals(requestJson.get("units"), weatherRequestFromJSON.getUnit());
    }

    @Test
    public void testIfForecastReportHasCorrectCity() {
        assertEquals(correctForecastReport.getCity(), actualForecastReport.getCity());
    }

    @Test
    public void testIfForecastReportHasCorrectCountry() {
        assertEquals(correctForecastReport.getCountry(), actualForecastReport.getCountry());
    }

    @Test
    public void testIfForecastReportHasCorrectLatitude() {
        assertEquals(correctForecastReport.getLatitude(), actualForecastReport.getLatitude(),0.01);
    }

    @Test
    public void testIfForecastReportHasCorrectLongitude() {
        assertEquals(correctForecastReport.getLongitude(), actualForecastReport.getLongitude(),0.01);
    }

    @Test
    public void testIfWeatherReportHasCorrectSingleDayObjectDayOne() {
        assertEquals(correctForecastReport.getDayOne().getMaxTemp(), actualForecastReport.getDayOne().getMaxTemp(), 0.01);
        assertEquals(correctForecastReport.getDayOne().getMinTemp(), actualForecastReport.getDayOne().getMinTemp(), 0.01);
    }

    @Test
    public void testIfWeatherReportHasCorrectSingleDayObjectDayTwo() {
        assertEquals(correctForecastReport.getDayTwo().getMaxTemp(), actualForecastReport.getDayTwo().getMaxTemp(), 0.01);
        assertEquals(correctForecastReport.getDayTwo().getMinTemp(), actualForecastReport.getDayTwo().getMinTemp(), 0.01);
    }

    @Test
    public void testIfWeatherReportHasCorrectSingleDayObjectDayThree() {
        assertEquals(correctForecastReport.getDayThree().getMaxTemp(), actualForecastReport.getDayThree().getMaxTemp(), 0.01);
        assertEquals(correctForecastReport.getDayThree().getMinTemp(), actualForecastReport.getDayThree().getMinTemp(), 0.01);
    }


}
