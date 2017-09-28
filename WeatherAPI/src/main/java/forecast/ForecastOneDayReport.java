package forecast;

/**
 * Created by Epu on 27.09.2017.
 */
public class ForecastOneDayReport {
    private double maxTemp;
    private double minTemp;

    public ForecastOneDayReport(double maxTemp, double minTemp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
