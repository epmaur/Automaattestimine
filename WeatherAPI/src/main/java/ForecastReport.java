/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastReport {
    private String city;
    private String country;
    private long longitude;
    private long latitude;
    private SingleDayReport dayOne;
    private SingleDayReport dayTwo;
    private SingleDayReport dayThree;

    public ForecastReport(String city, String country, long longitude, long latitude, SingleDayReport dayOne, SingleDayReport dayTwo, SingleDayReport dayThree) {
        this.city = city;
        this.country = country;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dayOne = dayOne;
        this.dayTwo = dayTwo;
        this.dayThree = dayThree;
    }

}
