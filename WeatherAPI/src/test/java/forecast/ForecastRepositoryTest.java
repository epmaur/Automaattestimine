package forecast;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Epu on 28.09.2017.
 */
public class ForecastRepositoryTest {
    private ForecastWeatherRequestFactory factory;
    private ForecastRepository forecastRepository;

    @Before
    public void initObjects() {
        factory = mock(ForecastWeatherRequestFactory.class);
        forecastRepository = new ForecastRepository(factory);
    }

    @Test
    public void testIfGetWeatherCallsJsonObjectBuilder() {
        forecastRepository.getWeather("Tallinn", "EE", "metric");
        verify(factory, times(1)).buildJSONObjectFromParameters(anyString(), anyString(), anyString());
    }


    @Test
    public void testIfGetWeatherCallsMakeForecastRequestMethod() {
        forecastRepository.getWeather("Tallinn", "EE", "metric");
        verify(factory, times(1)).makeWeatherRequestAndReturnResponseAsForecastReport(anyObject());
    }


}
