package forecast;

import org.json.simple.JSONObject;

/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastRepository {
    private ForecastWeatherRequestFactory factory;

    public ForecastRepository() {
        factory = new ForecastWeatherRequestFactory();
    }

    public ForecastRepository(ForecastWeatherRequestFactory factory) {
        this.factory = factory;
    }


    public ForecastReport getWeather(String city, String country, String units) {
        JSONObject request = factory.buildJSONObjectFromParameters(city, country, units);
        return factory.makeWeatherRequestAndReturnResponseAsForecastReport(request);
    }

}
