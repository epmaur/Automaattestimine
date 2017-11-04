package weatherRequest;

/**
 * Created by Epu on 27.09.2017.
 */
public class WeatherRequest {
    private String city;
    private String country;
    private String unit;

    public WeatherRequest(String city, String country, String unit) {
        this.city = city;
        this.country = country;
        this.unit = unit;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getUnit() { return unit; }

    @Override
    public String toString() {
        return "WeatherRequest{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", format='" + unit + '\'' +
                '}';
    }
}
