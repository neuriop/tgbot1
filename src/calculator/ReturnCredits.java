package calculator;

import calculator.credits.CreditTable;
import calculator.credits.MazdaCreditPB;
import calculator.credits.ToyotaCredit;

import java.util.ArrayList;
import java.util.List;

public class ReturnCredits {
    private List<CreditTable> credits = new ArrayList<>();

    public ReturnCredits() {
        credits.add(new ToyotaCredit());
        credits.add(new MazdaCreditPB());
    }

    public String returnCredits(double totalCost, double initialPayment, double monthlyPayment){
        String result = "";
        for (CreditTable credit : credits) {
            result += "\n\n" + credit.calculate(totalCost, initialPayment, monthlyPayment);
        }
        return result;
    }
}
