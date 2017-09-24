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
    private JSONObject jsonResponseForecastDayOne;
    private JSONObject jsonResponseForecastDayTwo;
    private JSONObject jsonResponseForecastDayThree;


    @Before
    public void initObjects() {
        parser = new JSONParser();
        try {
            //Temporarily using an example json file for testing
            jsonResponseAll = (JSONObject) parser.parse(new FileReader(getClass().getResource("exampleJSON.txt").getFile()));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        jsonResponseCoordinates = (JSONObject) jsonResponseAll.get("coordinates");
        jsonResponseForecast = (JSONObject) jsonResponseAll.get("forecast");
        jsonResponseForecastDayOne = (JSONObject) jsonResponseForecast.get("dayOne");
        jsonResponseForecastDayTwo = (JSONObject) jsonResponseForecast.get("dayTwo");
        jsonResponseForecastDayThree = (JSONObject) jsonResponseForecast.get("dayThree");
    }

    @Test
    public void testIfResponseContainsCity() {
        boolean hasCityKey = jsonResponseAll.containsKey("city");
        assertEquals(hasCityKey, true);
    }

    @Test
    public void testIfResponseContainsTemperatureNow() {
        boolean hasTemperatureNowKey = jsonResponseAll.containsKey("temperatureNow");
        assertEquals(hasTemperatureNowKey, true);
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
        boolean hasNextDayForecast = jsonResponseForecast.containsKey("dayOne");
        boolean hasSecondDayForecast = jsonResponseForecast.containsKey("dayTwo");
        boolean hasThirdDayForecast = jsonResponseForecast.containsKey("dayThree");
        assertEquals(hasNextDayForecast, true);
        assertEquals(hasSecondDayForecast, true);
        assertEquals(hasThirdDayForecast, true);
    }

    @Test
    public void testIfResponseForecastContainsAllKeys() {
        JSONObject [] forecastDays = new JSONObject [] {jsonResponseForecastDayOne, jsonResponseForecastDayTwo,
                jsonResponseForecastDayThree};
        for (JSONObject forecastDay : forecastDays) {
            assertEquals(forecastDay.containsKey("description"), true);
            assertEquals(forecastDay.containsKey("highestTemp"), true);
            assertEquals(forecastDay.containsKey("lowestTemp"), true);
        }
    }

    @Test
    public void testIfTemperaturesAreReasonable() {
        long temperatureNow = (long) jsonResponseAll.get("temperatureNow");
        long highestTempNextDay = (long) jsonResponseForecastDayOne.get("highestTemp");
        long lowestTempNextDay = (long) jsonResponseForecastDayOne.get("lowestTemp");
        long highestTempSecondDay = (long) jsonResponseForecastDayTwo.get("highestTemp");
        long lowestTempSecondDay = (long) jsonResponseForecastDayTwo.get("lowestTemp");
        long highestTempThirdDay = (long) jsonResponseForecastDayThree.get("highestTemp");
        long lowestTempThirdDay = (long) jsonResponseForecastDayThree.get("lowestTemp");
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
