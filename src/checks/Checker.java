package checks;

import calculator.CreditCalculator;
import calculator.ReturnCredits;

import java.util.ArrayList;
import java.util.List;

public class Checker implements Validator{
    List<Check> checks = new ArrayList<>();

    public Checker(){
        checks.add(new Are3Args());
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
        return string.split("\n");
    }

    private String returnTable(String[] strings){
        if (requestCheck(strings) == null){
            double[] nums = new double[3];
            for (int i = 0; i < nums.length; i++) {
                nums[i] = Double.parseDouble(strings[i]);
            }
            ReturnCredits returnCredits = new ReturnCredits();
            return returnCredits.returnCredits(nums[0], nums[1], nums[2]);
        }
        return requestCheck(strings);
    }

    public String processString(String string){
         if (requestCheck(stringSplitter(string)) == null){
             return returnTable(stringSplitter(string));
         }
         return requestCheck(stringSplitter(string));
    }

}
