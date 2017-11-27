package urlBuilder;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class URLBuilderTest {
    private URLBuilder urlBuilder;
    private String correctUrl;

    @Before
    public void initObjects() {
        urlBuilder = new URLBuilder();
        correctUrl  = "http://api.openweathermap.org/data/2.5/weather?q=Tallinn%2CEE&APPID=24f99e919834ab7ccbf49162e4fc38a4&units=metric";
    }

    @Test
    public void testIfURLBuilderMakesCorrectUrl() {
        assertEquals(correctUrl, urlBuilder.buildURL("weather", "Tallinn", "EE", "metric"));
    }


}
