package currentWeather;

/**
 * Created by Epu on 27.09.2017.
 */
public class CurrentWeatherRepository {
    private UpdateCurrentWeatherTask updateCurrentWeatherTask;

    public CurrentWeatherRepository() {
        updateCurrentWeatherTask = new UpdateCurrentWeatherTask();
    }

    public CurrentWeatherRepository(UpdateCurrentWeatherTask updateCurrentWeatherTask) {
        this.updateCurrentWeatherTask = updateCurrentWeatherTask;
    }

    public void getWeather(String option) {
        if (option.equals("console")) {
            updateCurrentWeatherTask.getUserInputFromConsoleAndWriteResponseToFile();
        } else if (option.equals("file")) {
            updateCurrentWeatherTask.getUserInputFromFileAndWriteResponseToFile();
        }
    }

    public static void main(String[] args) {
        CurrentWeatherRepository repository = new CurrentWeatherRepository();
        repository.getWeather("file");
    }

}
