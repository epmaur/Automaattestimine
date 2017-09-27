/**
 * Created by Epu on 27.09.2017.
 */
public class CurrentWeatherReport {
    private String city;
    private String country;
    private long temp;
    private long latitude;
    private long longitude;

    public CurrentWeatherReport(String city, String country, long temp, long latitude, long longitude) {
        this.city = city;
        this.country = country;
        this.temp = temp;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
