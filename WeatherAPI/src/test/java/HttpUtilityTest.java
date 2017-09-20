/**
 * Created by Epu on 11.09.2017.
 */
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.fail;

public class HttpUtilityTest {

    @Test
    public void testHttpConnection() {
        String strUrl = "http://stackoverflow.com/about";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAPIKey() {
        fail();
    }

}
