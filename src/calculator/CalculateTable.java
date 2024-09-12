package calculator;

import calculator.credits.CreditTable;

public class CalculateTable extends CreditCalculator {
    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit) {
        String result = "";
        result += "Calculated result for " + credit.getClass().getSimpleName();
        int lowDiffPrev = lowDiffPrev(totalCost, initialPayment, credit);
        int lowDiffNext = lowDiffNext(totalCost, initialPayment, monthlyPayment, credit);
        if (initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]) != initialPayment) {
            result += "Initial payment of " + initialPayment + " is not available. The closest available payment is " + initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]);
        }
        result += "\nInitial\t\tMonthly payment";
        result += "\npayment";
        for (int i : credit.getMonthList()) {
            result += "\t\t" + i;
        }
        result += "\n" + initialPayment + "\t";
        for (int i = 0; i < credit.getPercentTable()[lowDiffPrev].length; i++) {

            result += "\t" + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i]));
        }
        result += "\nThe closest monthly payment to " + monthlyPayment + " you can do is: " + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]));
        result += "\nTotal cost of the credit is: " + Math.ceil(totalCostCounter(totalCost, credit, lowDiffPrev, lowDiffNext));
        return result;
    }
}
