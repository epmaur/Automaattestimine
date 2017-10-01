package currentWeather;

/**
 * Created by Epu on 27.09.2017.
 */
public class CurrentWeatherReport {
    private String city;
    private String country;
    private double temp;
    private double latitude;
    private double longitude;

    public CurrentWeatherReport(String city, String country, double temp, double latitude, double longitude) {
        this.city = city;
        this.country = country;
        this.temp = temp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getTemp() {
        return temp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
