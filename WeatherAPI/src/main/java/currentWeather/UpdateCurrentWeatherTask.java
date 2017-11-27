package currentWeather;

import consoleInput.ConsoleInputReader;
import consoleInput.ConsoleInputValidator;
import fileReader.FileReader;
import fileWriter.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Scanner;

public class UpdateCurrentWeatherTask {
    private FileWriter writer = new FileWriter();
    private ConsoleInputReader consoleInputReader = new ConsoleInputReader();
    private ConsoleInputValidator consoleInputValidator = new ConsoleInputValidator();
    private FileReader reader = new FileReader();
    private CurrentWeatherRepository currentWeatherRepository;

    public UpdateCurrentWeatherTask(FileWriter writer, ConsoleInputReader consoleInputReader,
                                    ConsoleInputValidator consoleInputValidator, FileReader reader, CurrentWeatherRepository currentWeatherRepository) {
        this.writer = writer;
        this.consoleInputReader = consoleInputReader;
        this.consoleInputValidator = consoleInputValidator;
        this.reader = reader;
        this.currentWeatherRepository = currentWeatherRepository;
    }

    public UpdateCurrentWeatherTask() {
        writer = new FileWriter();
        consoleInputReader = new ConsoleInputReader();
        consoleInputValidator = new ConsoleInputValidator();
        reader = new FileReader();
        currentWeatherRepository = new CurrentWeatherRepository();
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
        CurrentWeatherReport report = currentWeatherRepository.getWeather(city, countryCode, units);
        writer.writeObjectToFile(report, "output.txt");


    }

    public void getUserInputFromFileAndWriteResponseToFile() {
        JSONArray userInput = reader.readInputFromFile("input.txt");
        for (int i = 0; i < userInput.size(); i++) {
            JSONObject line = (JSONObject) userInput.get(i);
            String city = (String) line.get("city");
            String country = (String) line.get("countryCode");
            String units = (String) line.get("units");
            CurrentWeatherReport report = currentWeatherRepository.getWeather(city, country, units);
            writer.writeObjectToFile(report, city + ".txt");
        }
    }


}
