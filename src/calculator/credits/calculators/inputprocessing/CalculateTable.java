package calculator.credits.calculators.inputprocessing;

import calculator.credits.tables.CreditTable;
import calculator.credits.calculators.CreditCalculator;

public class CalculateTable extends CreditCalculator {
    @Override
    public String calculate(double totalCost, int i, int j, boolean c, CreditTable credit) {
        return "";
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit) {
        StringBuilder result = new StringBuilder();
        result.append("Calculated result for ").append(credit.getClass().getSimpleName());
        int lowDiffPrev = lowDiffPrev(totalCost, initialPayment, credit);
        int lowDiffNext = lowDiffNext(totalCost, initialPayment, monthlyPayment, credit);
        if (initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]) != initialPayment) {
            result.append("Initial payment of ").append(initialPayment).append(" is not available. The closest available payment is ").append(initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]));
        }
        result.append("\nInitial\t\tMonthly payment");
        result.append("\npayment");
        for (int i : credit.getMonthList()) {
            result.append("\t\t").append(i);
        }
        result.append("\n").append(initialPayment).append("\t");
        for (int i = 0; i < credit.getPercentTable()[lowDiffPrev].length; i++) {
            result.append("\t").append(Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i])));
        }
        result.append("\nThe closest monthly payment to ").append(monthlyPayment).append(" you can do is: ").append(Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext])));
        result.append("\nTotal cost of the credit is: ").append(Math.ceil(totalCostCounter(totalCost, credit, lowDiffPrev, lowDiffNext)));
        return result.toString();
    }

    @Override
    public String calculate(double totalCost, int i, int j, CreditTable credit) {
        return "";
    }
}
