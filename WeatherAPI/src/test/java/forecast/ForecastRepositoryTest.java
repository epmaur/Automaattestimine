package forecast;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Epu on 28.09.2017.
 */
public class ForecastRepositoryTest {
    private UpdateForecastTask updateForecastTask;
    private ForecastRepository forecastRepository;

    @Before
    public void initObjects() {
        updateForecastTask = mock(UpdateForecastTask.class);
        forecastRepository = new ForecastRepository(updateForecastTask);
    }

    @Test
    public void testIfGetWeatherWithOptionConsoleCallsGetInputFromConsoleAndWriteToFileMethod() {
        forecastRepository.getWeather("console");
        verify(updateForecastTask, times(1)).getUserInputFromConsoleAndWriteResponseToFile();
    }

    @Test
    public void testIfGetWeatherWithOptionFileCallsGetInputFromFileAndWriteToFileMethod() {
        forecastRepository.getWeather("file");
        verify(updateForecastTask, times(1)).getUserInputFromFileAndWriteResponseToFile();
    }

}
