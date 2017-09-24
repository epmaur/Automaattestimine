import java.util.Random;

/**
 * Created by Epu on 24.09.2017.
 */
public class HttpUtilities {

    public int getResponseStatusCode() {
        Integer [] statusCodes = new Integer[] {200, 404, 418};
        Random generator = new Random();
        int randomArrayIndex = generator.nextInt(statusCodes.length);
        return statusCodes[randomArrayIndex];
    }

}
