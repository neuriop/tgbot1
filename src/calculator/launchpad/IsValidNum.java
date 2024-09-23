package calculator.launchpad;

import checks.Check;

import java.math.BigDecimal;

public class IsValidNum implements Check {
    @Override
    public boolean update(String[] strings) {
        try {
            for (String string : strings) {
                BigDecimal bd = new BigDecimal(string);
            }
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @Override
    public String errorMessage() {
        return "Provided string cannot be converted to number. Remove any unnecessary characters and try again.";
    }
}
