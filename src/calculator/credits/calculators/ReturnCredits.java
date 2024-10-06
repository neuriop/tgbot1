package calculator.credits.calculators;

import calculator.credits.tables.CreditTable;
import calculator.credits.tables.MazdaCreditPB;
import calculator.credits.tables.ToyotaCredit;

import java.util.ArrayList;
import java.util.List;

public class ReturnCredits {
    private List<CreditTable> credits = new ArrayList<>();

    public ReturnCredits() {
        credits.add(new ToyotaCredit());
        credits.add(new MazdaCreditPB());
    }

    public String returnCredits(double totalCost, double initialPayment, double monthlyPayment){
        StringBuilder result = new StringBuilder();
        for (CreditTable credit : credits) {
            result.append("\n\n").append(credit.calculate(totalCost, initialPayment, monthlyPayment));
        }
        System.out.println(result);
        return result.toString();
    }

    public String returnCredits(double totalCost, int i, int j){
        StringBuilder result = new StringBuilder();
        for (CreditTable credit : credits) {
            result.append("\n\n").append(credit.calculate(totalCost, i, j));
        }
        System.out.println(result);
        return result.toString();
    }
}
