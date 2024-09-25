package calculator.launchpad;

import checks.Check;
import checks.Validator;

import java.util.ArrayList;
import java.util.List;

public class LPChecker implements Validator {
    List<Check> checks = new ArrayList<>();

    public LPChecker(){
        checks.add(new Are5Args());
        checks.add(new IsValidNum());
    }

    @Override
    public String requestCheck(String[] strings) {
        for (Check check : checks) {
            if (!check.update(strings)){
                return check.errorMessage();
            }
        }
        return null;
    }

    private String[] stringSplitter(String string){
        String[] split = string.split("\n");
        return split;
    }

    private String returnCalculation(String[] strings){
        if (requestCheck(strings) == null){
            return "Your launchpool earnings are: " + new Launchpad().launchpoolCalculate(strings[0], strings[1], strings[2], strings[3], strings[4]);
        }
        return requestCheck(strings);
    }

    public String processString(String string){
        if (requestCheck(stringSplitter(string)) == null)
            return returnCalculation(stringSplitter(string));
        return requestCheck(stringSplitter(string));
    }

}
