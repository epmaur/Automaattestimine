package forecast;

import consoleInput.ConsoleInputReader;
import consoleInput.ConsoleInputValidator;
import fileReader.FileReader;
import fileWriter.FileWriter;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class UpdateForecastTask {

    private ForecastFactory factory;
    private FileWriter writer;
    private ConsoleInputReader consoleInputReader;
    private ConsoleInputValidator consoleInputValidator;
    private FileReader reader;

    public UpdateForecastTask(ForecastFactory factory, FileWriter writer, ConsoleInputReader consoleInputReader,
                              ConsoleInputValidator consoleInputValidator, FileReader reader) {
        this.factory = factory;
        this.writer = writer;
        this.consoleInputReader = consoleInputReader;
        this.consoleInputValidator = consoleInputValidator;
        this.reader = reader;
    }

    public UpdateForecastTask() {
        factory = new ForecastFactory();
        writer = new FileWriter();
        consoleInputReader = new ConsoleInputReader();
        consoleInputValidator = new ConsoleInputValidator();
        reader = new FileReader();
    }


    public void getUserInputFromConsoleAndWriteResponseToFile() {
        Scanner scanner = new Scanner(System.in);
        String city;
        String countryCode;
        String units;

        while (true) {
            city = consoleInputReader.readInputFromConsole("Enter city name: ", scanner);
            if (consoleInputValidator.isValidCity(city)) {
                break;
            } else {
                System.out.println("Not a valid city. Try again");
            }
        }

        while (true) {
            countryCode = consoleInputReader.readInputFromConsole("Enter country code: ", scanner);
            if (consoleInputValidator.isValidCountryCode(countryCode)) {
                break;
            } else {
                System.out.println("Not a valid country code. Try again");
            }
        }

        while (true) {
            units = consoleInputReader.readInputFromConsole("Enter unit: ", scanner);
            if (consoleInputValidator.isValidUnit(units)) {
                break;
            } else {
                System.out.println("Not a valid unit Try again");
            }
        }
        JSONObject userInput = factory.buildJSONObjectFromParameters(city, countryCode, units);
        JSONObject currentWeatherInJSON = factory.makeForecastRequestAndReturnResponseInJson(userInput);
        writer.writeJsonToFile(currentWeatherInJSON, "output.txt");


    }

    public void getUserInputFromFileAndWriteResponseToFile() {
        JSONObject userInput = reader.readInputFromFile("input.txt");
        JSONObject weatherResponse = factory.makeForecastRequestAndReturnResponseInJson(userInput);
        writer.writeJsonToFile(weatherResponse, "output.txt");
    }
}
