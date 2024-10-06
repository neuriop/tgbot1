package calculator.credits.calculators.inputprocessing;

import calculator.credits.tables.CreditTable;
import calculator.credits.calculators.CreditCalculator;

public class CalculateInput extends CreditCalculator {
    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j, CreditTable credit) {
        String result = "";
        double percent = credit.getPercentTable()[i][j];
        double initialPayment = credit.getInitialPayment()[j];
        int month = credit.getMonthList()[j];
        double initialCommission = credit.getInitialCommission()[j];
        result += "Calculated result for " + credit.getClass().getSimpleName();
        result += "\nTotal car cost: " + totalCost;
        result += "\nInitial prepayment: " + initialPayment * 100 + "% - " + totalCost * initialPayment;
        result += "\nMonthly payment: " + percent + "% - " + monthlyPayCounter(totalCost, initialPayment, month, percent);
        result += "\nInitial commission: " + initialCommission + "% - " + totalCost * (initialCommission / 100);
        result += "\nTotal credit cost: " + totalCostCounter(totalCost, initialPayment, percent, initialCommission, month);
        result += "\nOverpayment: " + (totalCostCounter(totalCost, initialPayment, percent, initialCommission, month) - totalCost);
        return result;
    }
}
