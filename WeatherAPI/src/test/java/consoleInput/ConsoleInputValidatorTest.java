package consoleInput;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ConsoleInputValidatorTest {

    private ConsoleInputValidator consoleInputValidator;
    private String correctCityName;
    private String incorrectCityName;
    private String correctCountryCode;
    private String incorrectCountryCode;
    private String correctUnit;
    private String incorrectUnit;

    @Before
    public void initObjects() {
        consoleInputValidator = new ConsoleInputValidator();
        correctCityName = "Tallinn";
        incorrectCityName = "T4llinn";
        correctCountryCode = "EE";
        incorrectCountryCode = "oijge";
        correctUnit = "metric";
        incorrectUnit = "notmetric";
    }

    @Test
    public void testCityValidatorWithCorrectCityName() {
        assertEquals(true, consoleInputValidator.isValidCity(correctCityName));
    }

    @Test
    public void testCityValidatorWithIncorrectCityName() {
        assertEquals(false, consoleInputValidator.isValidCity(incorrectCityName));
    }

    @Test
    public void testCountryValidatorWithCorrectCountryCode() {
        assertEquals(true, consoleInputValidator.isValidCountryCode(correctCountryCode));
    }

    @Test
    public void testCountryValidatorWithIncorrectCountryCode() {
        assertEquals(false, consoleInputValidator.isValidCountryCode(incorrectCountryCode));
    }

    @Test
    public void testUnitValidatorWithCorrectUnit() {
        assertEquals(true, consoleInputValidator.isValidUnit(correctUnit));
    }

    @Test
    public void testUnitValidatorWithIncorrectUnit() {
        assertEquals(false, consoleInputValidator.isValidUnit(incorrectUnit));
    }

}
