package utilities;

import currentWeather.CurrentWeatherFactory;
import forecast.ForecastFactory;
import org.junit.Before;
import org.junit.Test;
import weatherRequest.WeatherRequest;

import static org.junit.Assert.assertEquals;

/**
 * Created by Epu on 24.09.2017.
 */
public class HttpUtilitiesTest {
    private WeatherRequest weatherRequest;
    private CurrentWeatherFactory currentWeatherFactory;
    private String currentWeatherUrl;
    private ForecastFactory forecastFactory;
    private String forecastUrl;
    private HttpUtilities httpUtilities;


    @Before
    public void initObjects() {
        weatherRequest = new WeatherRequest("Tallinn", "EE", "metric");
        currentWeatherFactory = new CurrentWeatherFactory();
        currentWeatherUrl = currentWeatherFactory.buildCurrentWeatherURL(weatherRequest);
        forecastFactory = new ForecastFactory();
        forecastUrl = forecastFactory.buildForecastURL(weatherRequest);
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
