package currentWeather;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Epu on 28.09.2017.
 */
public class CurrentWeatherRepositoryTest {
    private UpdateCurrentWeatherTask updateCurrentWeatherTask;
    private CurrentWeatherRepository currentWeatherRepository;

    @Before
    public void initObjects() {
        updateCurrentWeatherTask = mock(UpdateCurrentWeatherTask.class);
        currentWeatherRepository = new CurrentWeatherRepository(updateCurrentWeatherTask);
    }

    @Test
    public void testIfGetWeatherWithOptionConsoleCallsGetInputFromConsoleAndWriteToFileMethod() {
        currentWeatherRepository.getWeather("console");
        verify(updateCurrentWeatherTask, times(1)).getUserInputFromConsoleAndWriteResponseToFile();
    }

    @Test
    public void testIfGetWeatherWithOptionFileCallsGetInputFromFileAndWriteToFileMethod() {
        currentWeatherRepository.getWeather("file");
        verify(updateCurrentWeatherTask, times(1)).getUserInputFromFileAndWriteResponseToFile();
    }


}
