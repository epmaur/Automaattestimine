package currentWeather;

import org.json.simple.JSONObject;
import weatherRequest.WeatherRequest;

/**
 * Created by Epu on 27.09.2017.
 */
public class CurrentWeatherRepository {
    private CurrentWeatherRequestFactory factory;

    public CurrentWeatherRepository() {
        factory = new CurrentWeatherRequestFactory();
    }

    public CurrentWeatherRepository(CurrentWeatherRequestFactory factory) {
        this.factory = factory;
    }


    public CurrentWeatherReport getWeather(String city, String country, String units) {
        JSONObject request = factory.buildWeatherRequestAsJSONObjectFromParameters(city, country, units);
        return factory.makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(request);
    }


}
