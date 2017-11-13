package forecast;

/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastRepository {
    private UpdateForecastTask forecastTask;

    public ForecastRepository() {
        forecastTask = new UpdateForecastTask();
    }

    public ForecastRepository(UpdateForecastTask forecastTask) {
        this.forecastTask = forecastTask;
    }

    public void getWeather(String option) {
        if (option.equals("console")) {
            forecastTask.getUserInputFromConsoleAndWriteResponseToFile();
        } else if (option.equals("file")) {
            forecastTask.getUserInputFromFileAndWriteResponseToFile();
        }
    }

}
