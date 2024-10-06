package calculator.launchpad;

import calculator.credits.checks.Check;

public class Are5Args implements Check {
    @Override
    public boolean update(String[] strings) {
        return strings.length == 5;
    }

    @Override
    public String errorMessage() {
        return "Invalid amount of arguments (5 required)";
    }
}
