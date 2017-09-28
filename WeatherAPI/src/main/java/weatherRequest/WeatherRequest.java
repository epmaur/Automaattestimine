package weatherRequest;

/**
 * Created by Epu on 27.09.2017.
 */
public class WeatherRequest {
    private String city;
    private String country;

    public WeatherRequest(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
