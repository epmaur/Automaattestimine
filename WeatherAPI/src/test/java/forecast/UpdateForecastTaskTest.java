package forecast;

import consoleInput.ConsoleInputReader;
import consoleInput.ConsoleInputValidator;
import fileReader.FileReader;
import fileWriter.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UpdateForecastTaskTest {
    private UpdateForecastTask updateForecastTask;
    private ConsoleInputReader consoleInputReader;
    private ForecastFactory factory;
    private ConsoleInputValidator validator;
    private FileWriter fileWriter;
    private FileReader fileReader;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private int numberOfCitiesInInputFile;

    @Before
    public void initObjects() {
        consoleInputReader = mock(ConsoleInputReader.class);
        factory = mock(ForecastFactory.class);
        validator = mock(ConsoleInputValidator.class);
        fileWriter = mock(FileWriter.class);
        fileReader = mock(FileReader.class);
        jsonObject = new JSONObject();
        jsonObject.put("randomKey", "randomValue");
        jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        updateForecastTask = new UpdateForecastTask(factory, fileWriter, consoleInputReader, validator, fileReader);
        when(validator.isValidCity(anyString())).thenReturn(true);
        when(validator.isValidCountryCode(anyString())).thenReturn(true);
        when(validator.isValidUnit(anyString())).thenReturn(true);
        when(fileReader.readInputFromFile(anyString())).thenReturn(jsonArray);
        numberOfCitiesInInputFile = jsonArray.size();

    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsReadFromConsoleMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(consoleInputReader, times(3)).readInputFromConsole(anyString(), anyObject());
        verify(validator, times(1)).isValidCity(anyString());
        verify(validator, times(1)).isValidCountryCode(anyString());
        verify(validator, times(1)).isValidUnit(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsCityValidatorMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidCity(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsCountryCodeValidatorMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidCountryCode(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsUnitValidatorMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(validator, times(1)).isValidUnit(anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsJSONObjectBuilderMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(factory, times(1)).buildJSONObjectFromParameters(anyString(), anyString(), anyString());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsWeatherRequestMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(factory, times(1)).makeForecastRequestAndReturnResponseInJson(anyObject());
    }

    @Test
    public void testIfReadFromConsoleAndWriteToFileCallsWriteToFileMethod() {
        updateForecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        verify(fileWriter, times(1)).writeObjectToFile(anyObject(), anyString());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileCallsReadFromFileMethod() {
        updateForecastTask.getUserInputFromFileAndWriteResponseToFile();
        verify(fileReader, times(1)).readInputFromFile(anyString());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileCallsWeatherRequestMethod() {
        updateForecastTask.getUserInputFromFileAndWriteResponseToFile();
        verify(factory, times(numberOfCitiesInInputFile)).makeWeatherRequestAndReturnResponseAsForecastReport(anyObject());
    }

    @Test
    public void testIfReadFromFileAndWriteToFileCallsWriteToFileMethod() {
        updateForecastTask.getUserInputFromFileAndWriteResponseToFile();
        verify(fileWriter, times(numberOfCitiesInInputFile)).writeObjectToFile(anyObject(), anyString());
    }
}
