package currentWeather;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import urlBuilder.URLBuilder;
import weatherRequest.WeatherRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class CurrentWeatherRequestFactory {
    private URLBuilder urlBuilder;

    public CurrentWeatherRequestFactory(URLBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    public CurrentWeatherRequestFactory() {
        this.urlBuilder = new URLBuilder();
    }


    public JSONObject makeCurrentWeatherRequestAndReturnResponseInJson(JSONObject json) {
        WeatherRequest weatherRequest = buildWeatherRequestFromJSON(json);
        String url = urlBuilder.buildURL("weather", weatherRequest.getCity(), weatherRequest.getCountry(), weatherRequest.getUnit());
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

    public CurrentWeatherReport makeWeatherReportFromJsonResponse(JSONObject response) {
        JSONObject sys = (JSONObject) response.get("sys");
        JSONObject main = (JSONObject) response.get("main");
        JSONObject coord = (JSONObject) response.get("coord");
        String city = (String) response.get("name");
        String country = (String) sys.get("country");
        long temp = (long) main.get("temp");
        double longitude = (double) coord.get("lon");
        double latitude = (double) coord.get("lat");
        CurrentWeatherReport currentWeatherReport = new CurrentWeatherReport(city, country, temp, latitude, longitude);
        return currentWeatherReport;
    }


    public CurrentWeatherReport makeWeatherRequestAndReturnResponseAsCurrentWeatherReport(JSONObject json) {
        JSONObject weatherReportInJson = makeCurrentWeatherRequestAndReturnResponseInJson(json);
        return makeWeatherReportFromJsonResponse(weatherReportInJson);
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
