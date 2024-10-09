package calculator.credits.tables;

import calculator.credits.calculators.inputprocessing.CalculateWithCondition;

public class RenaultCreditPB extends CreditTable {

    public RenaultCreditPB() {
        calculator = new CalculateWithCondition();
        percentTable = new double[][]{
                {9.99, 15.9},
                {8.99, 15.9},
                {6.99, 15.9},
                {6.99, 15.9}};
        initialPayment = new double[]{0.2, 0.3, 0.4, 0.5};
        monthList = new int[]{24, 36};
        initialCommission = new double[]{2.9, 2.9};
        kasko = 6.3;
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j, boolean c) {
        return "";
    }
}
