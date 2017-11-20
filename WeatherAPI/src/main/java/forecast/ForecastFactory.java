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

public class ForecastFactory {

    public static final String APIKey = "24f99e919834ab7ccbf49162e4fc38a4";

    public String buildForecastURL(WeatherRequest weatherRequest) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/forecast")
                .addParameter("q", weatherRequest.getCity() + "," + weatherRequest.getCountry())
                .addParameter("APPID", APIKey)
                .addParameter("units", weatherRequest.getUnit());
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }

    public JSONObject makeForecastRequestAndReturnResponseInJson(JSONObject object) {
        WeatherRequest weatherRequest = buildWeatherRequestFromJSON(object);
        String url = buildForecastURL(weatherRequest);
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

    public ForecastReport makeWeatherRequestAndReturnResponseAsForecastReport(JSONObject jsonObject) {
        JSONObject forecastReportInJson = makeForecastRequestAndReturnResponseInJson(jsonObject);
        JSONObject cityObject = (JSONObject) forecastReportInJson.get("city");
        JSONObject coord = (JSONObject) cityObject.get("coord");
        double latitude = (double) coord.get("lat");
        double longitude = (double) coord.get("lon");
        String cityName = (String) cityObject.get("name");
        String country = (String) cityObject.get("country");
        ForecastOneDayReport dayOne = makeSingleDayReport(forecastReportInJson, 1);
        ForecastOneDayReport dayTwo = makeSingleDayReport(forecastReportInJson, 2);
        ForecastOneDayReport dayThree = makeSingleDayReport(forecastReportInJson, 3);
        ForecastReport forecastReport = new ForecastReport(cityName, country, longitude, latitude, dayOne, dayTwo, dayThree);
        return forecastReport;
    }


    public ForecastOneDayReport makeSingleDayReport (JSONObject forecastObject, int day) {
        int dayOfMonthToday = (new Timestamp(System.currentTimeMillis())).toLocalDateTime().getDayOfMonth();
        JSONArray forecast = (JSONArray) forecastObject.get("list");
        double previousMaxTemp = Integer.MIN_VALUE;
        double previousMinTemp = Integer.MAX_VALUE;
        for (int i = 0; i < forecast.size(); i++) {
            JSONObject singleForecast = (JSONObject) forecast.get(i);
            Timestamp timestamp = new Timestamp((Long) singleForecast.get("dt") * 1000);
            JSONObject main = (JSONObject) singleForecast.get("main");
            Object minTempObj = main.get("temp_min");
            Object maxTempObj = main.get("temp_max");
            double minTemp = new Double(minTempObj.toString());
            double maxTemp = new Double(maxTempObj.toString());
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

    public JSONObject buildJSONObjectFromParameters(String city, String countryCode, String units) {
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
