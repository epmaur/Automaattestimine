package forecast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;

/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastRepository {
    public static final String APIKey = "24f99e919834ab7ccbf49162e4fc38a4";

    public static String buildForecastURL(WeatherRequest weatherRequest) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/forecast")
                .addParameter("q", weatherRequest.getCity() + "," + weatherRequest.getCountry())
                .addParameter("APPID", APIKey);
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public static JSONObject makeForecastRequest(WeatherRequest weatherRequest) throws IOException, ParseException {
        String url = buildForecastURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = client.execute(httpRequest);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        return jsonObject;
    }

    public static ForecastReport makeJSONResponseIntoForecastReport(WeatherRequest weatherRequest) throws IOException, ParseException {
        JSONObject forecastReportInJason = makeForecastRequest(weatherRequest);
        JSONObject cityObject = (JSONObject) forecastReportInJason.get("city");
        JSONObject coord = (JSONObject) cityObject.get("coord");
        double latitude = (double) coord.get("lat");
        double longitude = (double) coord.get("lon");
        String cityName = (String) cityObject.get("name");
        String country = (String) cityObject.get("country");
        ForecastOneDayReport dayOne = makeSingleDayReport(forecastReportInJason, 1);
        ForecastOneDayReport dayTwo = makeSingleDayReport(forecastReportInJason, 2);
        ForecastOneDayReport dayThree = makeSingleDayReport(forecastReportInJason, 3);
        ForecastReport forecastReport = new ForecastReport(cityName, country, longitude, latitude, dayOne, dayTwo, dayThree);
        return forecastReport;
    }

    public static ForecastOneDayReport makeSingleDayReport (JSONObject forecastObject, int day) {
        int dayOfMonthToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
        JSONArray forecast = (JSONArray) forecastObject.get("list");
        double previousMaxTemp = Integer.MIN_VALUE;
        double previousMinTemp = Integer.MAX_VALUE;

        for (int i = 0; i < forecast.size(); i++) {
            JSONObject singleForecast = (JSONObject) forecast.get(i);
            Timestamp timestamp = new Timestamp((Long) singleForecast.get("dt"));
            JSONObject main = (JSONObject) singleForecast.get("main");
            double minTemp = (double) main.get("temp_min");
            double maxTemp = (double) main.get("temp_max");
            int numberOfDaysFromToday = timestamp.toLocalDateTime().getDayOfMonth() - dayOfMonthToday;
            if (numberOfDaysFromToday == day) {
                if (minTemp < previousMinTemp) {
                    previousMinTemp = minTemp;
                }
                if (maxTemp > previousMaxTemp) {
                    previousMaxTemp = maxTemp;
                }
            }
        }
        ForecastOneDayReport singleDayReport = new ForecastOneDayReport(previousMaxTemp, previousMinTemp);
        return singleDayReport;
    }

}
