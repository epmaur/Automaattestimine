package forecast;

/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastReport {
    private String city;
    private String country;
    private double longitude;
    private double latitude;
    private ForecastOneDayReport dayOne;
    private ForecastOneDayReport dayTwo;
    private ForecastOneDayReport dayThree;

    public ForecastReport(String city, String country, double longitude, double latitude, ForecastOneDayReport dayOne, ForecastOneDayReport dayTwo, ForecastOneDayReport dayThree) {
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dayOne = dayOne;
        this.dayTwo = dayTwo;
        this.dayThree = dayThree;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public ForecastOneDayReport getDayOne() {
        return dayOne;
    }

    public ForecastOneDayReport getDayTwo() {
        return dayTwo;
    }

    public ForecastOneDayReport getDayThree() {
        return dayThree;
    }
}
