package consoleInput;

import utilities.Constants;

public class ConsoleInputValidator {

    public boolean isValidCity(String city) {
        if (!city.isEmpty() && city.matches("[a-zA-Z]+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidCountryCode(String countryCode) {
        for (Constants.COUNTRYCODE code: Constants.COUNTRYCODE.values()) {
            if (code.toString().equals(countryCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidUnit(String units) {
        for (Constants.UNIT unit: Constants.UNIT.values()) {
            if (unit.toString().equals(units)) {
                return true;
            }
        }
        return false;
    }
}
