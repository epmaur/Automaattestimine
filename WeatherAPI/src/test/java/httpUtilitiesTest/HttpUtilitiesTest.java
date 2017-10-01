package httpUtilitiesTest;

import currentWeather.CurrentWeatherRepository;
import forecast.ForecastRepository;
import httpUtilities.HttpUtilities;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Epu on 24.09.2017.
 */
public class HttpUtilitiesTest {
    private WeatherRequest weatherRequest;
    private CurrentWeatherRepository currentWeatherRepository;
    private String currentWeatherUrl;
    private ForecastRepository forecastRepository;
    private String forecastUrl;
    private HttpUtilities httpUtilities;


    @Before
    public void initObjects() {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        currentWeatherRepository = new CurrentWeatherRepository();
        currentWeatherUrl =currentWeatherRepository.buildCurrentWeatherURL(weatherRequest);
        forecastRepository = new ForecastRepository();
        forecastUrl = forecastRepository.buildForecastURL(weatherRequest);
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
