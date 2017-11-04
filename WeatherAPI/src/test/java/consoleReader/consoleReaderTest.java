package consoleReader;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class consoleReaderTest {
    private ConsoleReader consoleReader;
    private String correctCityName;
    private String incorrectCityName;
    private String correctCountryCode;
    private String incorrectCountryCode;
    private String correctUnit;
    private String incorrectUnit;

    @Before
    public void initObjects() {
        consoleReader = new ConsoleReader();
        correctCityName = "Tallinn";
        incorrectCityName = "T4llinn";
        correctCountryCode = "EE";
        incorrectCountryCode = "oijge";
        correctUnit = "metric";
        incorrectUnit = "notmetric";
    }

    @Test
    public void testCityValidatorWithCorrectCityName() {
        assertEquals(true, consoleReader.isValidCity(correctCityName));
    }

    @Test
    public void testCityValidatorWithIncorrectCityName() {
        assertEquals(false, consoleReader.isValidCity(incorrectCityName));
    }

    @Test
    public void testCountryValidatorWithCorrectCountryCode() {
        assertEquals(true, consoleReader.isValidCountryCode(correctCountryCode));
    }

    @Test
    public void testCountryValidatorWithIncorrectCountryCode() {
        assertEquals(false, consoleReader.isValidCountryCode(incorrectCountryCode));
    }

    @Test
    public void testUnitValidatorWithCorrectUnit() {
        assertEquals(true, consoleReader.isValidUnit(correctUnit));
    }

    @Test
    public void testUnitValidatorWithIncorrectUnit() {
        assertEquals(false, consoleReader.isValidUnit(incorrectUnit));
    }







}
