package calculator.credits.checks;

public class IsValidNum implements Check {
    @Override
    public String errorMessage() {
        return "Provided number is not valid.";
    }

    @Override
    public boolean update(String[] strings) {
        try {
            for (String string : strings) {
                Double.parseDouble(string);
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
