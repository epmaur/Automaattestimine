import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Epu on 23.09.2017.
 */
public class Json {
    public static void parseJSONFile() throws JSONException {

        JSONParser parser = new JSONParser();

        try {
            //File file = new File("C:\Users\Epu\Desktop/json.txt");
            //System.out.println(file.getAbsoluteFile().exists());
            Object obj = parser.parse(new FileReader("C:/Users/Epu/Desktop/json.txt"));

            org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;
            String city = (String) jsonObject.get("city");
            org.json.simple.JSONObject object = (org.json.simple.JSONObject) jsonObject.get("forecast");
            LocalDateTime dateTimeNow = LocalDateTime.now();
            String lastModifiedString = (String) jsonObject.get("lastModified");
            LocalDateTime lastModifiedDate = LocalDateTime.parse(lastModifiedString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.println(lastModifiedDate.isAfter(dateTimeNow.minusHours(3)));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws JSONException {
        parseJSONFile();
    }
}


