package utilities;

import currentWeather.CurrentWeatherRequestFactory;
import forecast.ForecastWeatherRequestFactory;
import org.junit.Before;
import org.junit.Test;
import urlBuilder.URLBuilder;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Epu on 24.09.2017.
 */
public class HttpUtilitiesTest {
    private WeatherRequest weatherRequest;
    private String currentWeatherUrl;
    private String forecastUrl;
    private HttpUtilities httpUtilities;
    private URLBuilder urlBuilder;


    @Before
    public void initObjects() {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        urlBuilder = new URLBuilder();
        currentWeatherUrl = urlBuilder.buildURL("weather", weatherRequest.getCity(), weatherRequest.getCountry(), weatherRequest.getUnit());
        forecastUrl = urlBuilder.buildURL("forecast", weatherRequest.getCity(), weatherRequest.getCountry(), weatherRequest.getUnit());
        httpUtilities = new HttpUtilities();
    }

    @Test
    public void testIfCurrentWeatherResponseStatusCodeIsOK() {
        assertEquals(200, httpUtilities.getResponseStatusCode(currentWeatherUrl));
    }

    @Test
    public void testIfWeatherForecastResponseStatusCodeIsOK() {
        assertEquals(200, httpUtilities.getResponseStatusCode(forecastUrl));
    }


}
