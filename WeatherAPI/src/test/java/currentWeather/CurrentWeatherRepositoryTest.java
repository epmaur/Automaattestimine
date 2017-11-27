package currentWeather;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Epu on 28.09.2017.
 */
public class CurrentWeatherRepositoryTest {
    private CurrentWeatherRepository currentWeatherRepository;
    private CurrentWeatherRequestFactory factory;


    @Before
    public void initObjects() {
        factory = mock(CurrentWeatherRequestFactory.class);
        currentWeatherRepository = new CurrentWeatherRepository(factory);
    }

    @Test
    public void testIfGetWeatherCallsJsonObjectBuilderMethod() {
        currentWeatherRepository.getWeather("Tallinn", "EE", "metric");
        verify(factory, times(1)).buildWeatherRequestAsJSONObjectFromParameters(anyString(),anyString(), anyString());
    }

    @Test
    public void testIfGetWeatherCallsMakeWeatherRequestMethod() {
        currentWeatherRepository.getWeather("Tallinn", "EE", "metric");
        verify(factory, times(1)).makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(anyObject());
    }

}
