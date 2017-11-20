package currentWeather;

import consoleInput.ConsoleInputReader;
import consoleInput.ConsoleInputValidator;
import fileReader.FileReader;
import fileWriter.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class UpdateCurrentWeatherTask {
    private CurrentWeatherFactory factory = new CurrentWeatherFactory();
    private FileWriter writer = new FileWriter();
    private ConsoleInputReader consoleInputReader = new ConsoleInputReader();
    private ConsoleInputValidator consoleInputValidator = new ConsoleInputValidator();
    private FileReader reader = new FileReader();

    public UpdateCurrentWeatherTask(CurrentWeatherFactory factory, FileWriter writer, ConsoleInputReader consoleInputReader,
                                    ConsoleInputValidator consoleInputValidator, FileReader reader) {
        this.factory = factory;
        this.writer = writer;
        this.consoleInputReader = consoleInputReader;
        this.consoleInputValidator = consoleInputValidator;
        this.reader = reader;
    }

    public UpdateCurrentWeatherTask() {
        factory = new CurrentWeatherFactory();
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
        JSONObject userInput = factory.buildWeatherRequestAsJSONObjectFromParameters(city, countryCode, units);
        CurrentWeatherReport report = factory.makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(userInput);
        writer.writeObjectToFile(report, "output.txt");


    }

    public void getUserInputFromFileAndWriteResponseToFile() {
        JSONArray userInput = reader.readInputFromFile("input.txt");
        for (int i = 0; i < userInput.size(); i++) {
            JSONObject line = (JSONObject) userInput.get(i);
            CurrentWeatherReport report = factory.makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(line);
            String city = (String) line.get("city");
            writer.writeObjectToFile(report, city + ".txt");
        }
    }


}
