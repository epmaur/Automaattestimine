import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Epu on 27.09.2017.
 */
public class CurrentWeatherRepository {
    public static final String APIKey = "24f99e919834ab7ccbf49162e4fc38a4";


    public static String buildCurrentWeatherURL(WeatherRequest request) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/weather")
                .addParameter("q", request.getCity() + "," + request.getCountry())
                .addParameter("APPID", APIKey);
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public static JSONObject makeCurrentWeatherRequest(WeatherRequest weatherRequest) throws IOException, ParseException {
        String url = buildCurrentWeatherURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = client.execute(httpRequest);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        return jsonObject;
    }

    public static CurrentWeatherReport makeJSONResponseIntoWeatherReport(WeatherRequest weatherRequest) throws IOException, ParseException {
        JSONObject weatherReportInJson = makeCurrentWeatherRequest(weatherRequest);
        JSONObject sys = (JSONObject) weatherReportInJson.get("sys");
        JSONObject main = (JSONObject) weatherReportInJson.get("main");
        JSONObject coord = (JSONObject) weatherReportInJson.get("coord");
        String city = (String) weatherReportInJson.get("name");
        String country = (String) sys.get("country");
        long temp = (long) main.get("temp");
        long longitude = (long) coord.get("lon");
        long latitude = (long) coord.get("lat");
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(city, country, temp, latitude, longitude);
        return currentWeatherReport;
    }


}
