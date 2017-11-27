package currentWeather;

import consoleInput.ConsoleInputReader;
import consoleInput.ConsoleInputValidator;
import fileReader.FileReader;
import fileWriter.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class UpdateCurrentWeatherTaskTest {
    private UpdateCurrentWeatherTask updateCurrentWeatherTask;
    private ConsoleInputReader consoleInputReader;
    private CurrentWeatherRepository repository;
    private ConsoleInputValidator validator;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private int numberOfCitiesInInputFile;


    @Before
    public void initObjects() {
        consoleInputReader = mock(ConsoleInputReader.class);
        repository = mock(CurrentWeatherRepository.class);
        validator = mock(ConsoleInputValidator.class);
        fileWriter = mock(FileWriter.class);
        fileReader = mock(FileReader.class);

        jsonObject = new JSONObject();
        jsonObject.put("randomKey", "randomValue");
        jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        updateCurrentWeatherTask = new UpdateCurrentWeatherTask(fileWriter, consoleInputReader, validator, fileReader, repository);
        when(validator.isValidCity(anyString())).thenReturn(true);
        when(validator.isValidCountryCode(anyString())).thenReturn(true);
        when(validator.isValidUnit(anyString())).thenReturn(true);
        when(fileReader.readInputFromFile(anyString())).thenReturn(jsonArray);
        numberOfCitiesInInputFile = jsonArray.size();

    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsReadFromConsoleMethod() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(consoleInputReader, times(3)).readInputFromConsole(anyString(), anyObject());
        verify(validator, times(1)).isValidCity(anyString());
        verify(validator, times(1)).isValidCountryCode(anyString());
        verify(validator, times(1)).isValidUnit(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsCityValidatorMethod() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidCity(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsCountryCodeValidatorMethod() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidCountryCode(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsUnitValidatorMethod() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidUnit(anyString());
    }


    @Test
    public void testIfReadFromConsoleAndWriteToFileAsksForWeather() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(repository, times(1)).getWeather(anyString(), anyString(), anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsWriteToFileMethod() {
        updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(fileWriter, times(1)).writeObjectToFile(anyObject(), anyString());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileCallsReadFromFileMethod() {
        updateCurrentWeatherTask.getUserInputFromFileAndWriteResponseToFile();
        verify(fileReader, times(1)).readInputFromFile(anyString());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileAsksForWeather() {
        updateCurrentWeatherTask.getUserInputFromFileAndWriteResponseToFile();
        verify(repository, times(numberOfCitiesInInputFile)).getWeather(anyString(), anyString(), anyString());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileCallsWriteToFileMethod() {
        updateCurrentWeatherTask.getUserInputFromFileAndWriteResponseToFile();
        verify(fileWriter, times(numberOfCitiesInInputFile)).writeObjectToFile(anyObject(), anyString());
    }

}
