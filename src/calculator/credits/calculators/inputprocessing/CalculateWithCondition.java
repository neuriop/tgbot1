package calculator.credits.calculators.inputprocessing;

import calculator.credits.calculators.CreditCalculator;
import calculator.credits.tables.CreditTable;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculateWithCondition extends CreditCalculator {
    @Override
    public String calculate(double totalCost, int i, int j, boolean c, CreditTable credit) {
        String result = "";
        BigDecimal totalCost1 = new BigDecimal(totalCost);
        BigDecimal percent = BigDecimal.valueOf(credit.getPercentTable()[i][j]);
        BigDecimal initialPayment = BigDecimal.valueOf(credit.getInitialPayment()[j]);
        BigDecimal month = BigDecimal.valueOf(credit.getMonthList()[j]);
        BigDecimal initialCommission = BigDecimal.valueOf(credit.getInitialCommission()[j]);
        BigDecimal kasko = BigDecimal.valueOf(credit.getKasko());
        result += "Calculated result for " + credit.getClass().getSimpleName();
        result += "\nTotal car cost: " + totalCost;
        result += "\nInitial prepayment: " + initialPayment.multiply(new BigDecimal(100)) + "% - " + totalCost1.multiply(initialPayment);
        result += "\nMonthly payment: " + percent + "% - " + monthlyPayCounter(totalCost1, initialPayment, month, percent);
        result += "\nInitial commission: " + initialCommission + "% - " + totalCost1.multiply(initialCommission.divide(new BigDecimal(100), new MathContext(2)));
        result += "\nTotal credit cost: " +  totalCostCounter(totalCost1, initialPayment, percent, initialCommission, month);
        result += "\nOverpayment: " + totalCostCounter(totalCost1, initialPayment, percent, initialCommission, month).subtract(totalCost1);
        if (c){
            result += "\nKasko payment: " + kasko + "% - " + totalCost1.multiply(kasko.divide(new BigDecimal(100), new MathContext(2)));
            result += "\nMonthly payment with kasko: " + totalCost1.multiply(kasko.divide(new BigDecimal(100), new MathContext(2))).add(monthlyPayCounter(totalCost1, initialPayment, month, percent));
            result += "\nTotal credit cost with kasko: " + totalCostCounter(totalCost1, initialPayment, percent, initialCommission, month).subtract(totalCost1).add(totalCost1.multiply(kasko.divide(new BigDecimal(100), new MathContext(2))).multiply(month));
        }
        return result;
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j, CreditTable credit) {
        return "";
    }
}
