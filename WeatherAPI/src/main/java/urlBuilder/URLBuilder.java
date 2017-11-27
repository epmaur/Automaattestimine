package urlBuilder;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLBuilder {
    public static final String APIKey = "24f99e919834ab7ccbf49162e4fc38a4";

    public String buildURL(String typeOfWeather, String city, String country, String units) {
        URIBuilder builder = new URIBuilder()
                .setScheme("http")
                .setHost("api.openweathermap.org")
                .setPath("/data/2.5/" + typeOfWeather)
                .addParameter("q", city + "," + country)
                .addParameter("APPID", APIKey)
                .addParameter("units", units);
        URL url = null;
        try {
            url = builder.build().toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return url.toString();
    }


}
