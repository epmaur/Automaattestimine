package currentWeather;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import weatherRequest.WeatherRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CurrentWeatherFactory {
    public static final String APIKey = "24f99e919834ab7ccbf49162e4fc38a4";


    public String buildCurrentWeatherURL(WeatherRequest request) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/weather")
                .addParameter("q", request.getCity() + "," + request.getCountry())
                .addParameter("APPID", APIKey)
                .addParameter("units", request.getUnit());
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeCurrentWeatherRequestAndReturnResponseInJson(JSONObject json) {
        WeatherRequest weatherRequest = buildWeatherRequestFromJSON(json);
        String url = buildCurrentWeatherURL(weatherRequest);
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpRequest = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = client.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public CurrentWeatherReport makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(JSONObject json) {
        JSONObject weatherReportInJson = makeCurrentWeatherRequestAndReturnResponseInJson(json);
        JSONObject sys = (JSONObject) weatherReportInJson.get("sys");
        JSONObject main = (JSONObject) weatherReportInJson.get("main");
        JSONObject coord = (JSONObject) weatherReportInJson.get("coord");
        String city = (String) weatherReportInJson.get("name");
        String country = (String) sys.get("country");
        long temp = (long) main.get("temp");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(city, country, temp, latitude, longitude);
        return currentWeatherReport;
    }

    public JSONObject buildWeatherRequestAsJSONObjectFromParameters(String city, String countryCode, String units) {
        JSONObject object = new JSONObject();
        object.put("city", city);
        object.put("countryCode", countryCode);
        object.put("units", units);
        return object;
    }

    public WeatherRequest buildWeatherRequestFromJSON(JSONObject jsonObject) {
        String city = (String) jsonObject.get("city");
        String countryCode = (String) jsonObject.get("countryCode");
        String units = (String) jsonObject.get("units");
        return new WeatherRequest(city, countryCode, units);
    }


}
