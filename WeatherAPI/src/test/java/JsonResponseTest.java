/**
 * Created by Epu on 11.09.2017.
 */
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


public class JsonResponseTest {
    private JSONParser parser;
    private JSONObject jsonResponseAll;
    private JSONObject jsonResponseCoordinates;
    private JSONObject jsonResponseForecast;
    private JSONObject jsonResponseForecastTomorrow;
    private JSONObject jsonResponseForecastSecondDay;
    private JSONObject jsonResponseForecastThirdDay;


    @Before
    public void initObjects() {
        parser = new JSONParser();
        try {
            //Temporarily using a json file for response
            jsonResponseAll = (JSONObject) parser.parse(new FileReader("C:/Users/Epu/Desktop/json.txt"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        jsonResponseCoordinates = (JSONObject) jsonResponseAll.get("coordinates");
        jsonResponseForecast = (JSONObject) jsonResponseAll.get("forecast");
        jsonResponseForecastTomorrow = (JSONObject) jsonResponseForecast.get("tomorrow");
        jsonResponseForecastSecondDay = (JSONObject) jsonResponseForecast.get("secondDay");
        jsonResponseForecastThirdDay = (JSONObject) jsonResponseForecast.get("thirdDay");
    }

    @Test
    public void testIfResponseContainsCity() {
        boolean hasCityKey = jsonResponseAll.containsKey("city");
        assertEquals(hasCityKey, true);
    }

    @Test
    public void testIfResponseContainsTemperatureNow() {
        boolean hasTemperatureKey = jsonResponseAll.containsKey("temperature");
        assertEquals(hasTemperatureKey, true);
    }

    @Test
    public void testIfResponseContainsCoordinates() {
        boolean hasLatitude = jsonResponseCoordinates.containsKey("latitude");
        boolean hasLongitude = jsonResponseCoordinates.containsKey("longitude");
        assertEquals(hasLatitude, true);
        assertEquals(hasLongitude, true);
    }

    @Test
    public void testIfResponseContainsThreeDayForecast() {
        boolean hasNextDayForecast = jsonResponseForecast.containsKey("tomorrow");
        boolean hasSecondDayForecast = jsonResponseForecast.containsKey("secondDay");
        boolean hasThirdDayForecast = jsonResponseForecast.containsKey("thirdDay");
        assertEquals(hasNextDayForecast, true);
        assertEquals(hasSecondDayForecast, true);
        assertEquals(hasThirdDayForecast, true);
    }

    @Test
    public void testIfResponseForecastContainsAllKeys() {
        JSONObject [] forecastDays = new JSONObject [] {jsonResponseForecastTomorrow, jsonResponseForecastSecondDay,
                jsonResponseForecastThirdDay};
        for (JSONObject forecastDay : forecastDays) {
            assertEquals(forecastDay.containsKey("description"), true);
            assertEquals(forecastDay.containsKey("highestTemp"), true);
            assertEquals(forecastDay.containsKey("lowestTemp"), true);
        }
    }

    @Test
    public void testIfTemperaturesAreReasonable() {
        long temperatureNow = (long) jsonResponseAll.get("temperature");
        long highestTempNextDay = (long) jsonResponseForecastTomorrow.get("highestTemp");
        long lowestTempNextDay = (long) jsonResponseForecastTomorrow.get("lowestTemp");
        long highestTempSecondDay = (long) jsonResponseForecastSecondDay.get("highestTemp");
        long lowestTempSecondDay = (long) jsonResponseForecastSecondDay.get("lowestTemp");
        long highestTempThirdDay = (long) jsonResponseForecastThirdDay.get("highestTemp");
        long lowestTempThirdDay = (long) jsonResponseForecastThirdDay.get("lowestTemp");
        Long [] temperatures = new Long[] {temperatureNow, highestTempNextDay, lowestTempNextDay,
                highestTempSecondDay, lowestTempSecondDay, highestTempThirdDay, lowestTempThirdDay};
        for (long temperature : temperatures) {
            boolean isTemperatureReasonable = temperature > -100 && temperature < 100;
            assertEquals(isTemperatureReasonable, true);
        }
    }

    @Test
    public void testIfCoordinatesAreReasonable() {
        double latitude = (double) jsonResponseCoordinates.get("latitude");
        double longitude = (double) jsonResponseCoordinates.get("longitude");
        boolean isLatitudeReasonable = latitude <= 90 && latitude >= -90;
        boolean isLongitudeReasonable = longitude <= 180 && longitude >= -180;
        assertEquals(isLatitudeReasonable, true);
        assertEquals(isLongitudeReasonable,  true);
    }

    @Test
    public void testIfDataHasBeenRenewedInLastThreeHours() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String lastModifiedString = (String) jsonResponseAll.get("lastModified");
        LocalDateTime lastModifiedDate = LocalDateTime.parse(lastModifiedString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertEquals(lastModifiedDate.isAfter(dateTimeNow.minusHours(3)), true);
    }

}
