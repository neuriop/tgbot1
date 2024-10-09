package calculator.credits.tables;

import calculator.credits.calculators.inputprocessing.CalculateInput;

public class MazdaCreditOB extends CreditTable {

    public MazdaCreditOB() {
        calculator = new CalculateInput();
        percentTable = new double[][]{
                {5.99, 8.99, 10.49, 13.99, 14.49},
                {4.49, 7.99, 9.49, 12.99, 13.49},
                {2.99, 6.99, 8.49, 12.49, 12.99},
                {0.01, 3.99, 6.49, 10.99, 11.99},
                {0.01, 0.01, 3.99, 8.99, 9.99},
                {0.01, 0.01, 0.01, 5.49, 6.99}};
        initialPayment = new double[]{0.2, 0.3, 0.4, 0.5, 0.6, 0.7};
        monthList = new int[]{12, 24, 36, 60, 84};
        initialCommission = new double[]{1.99, 1.99, 1.99, 0, 0};
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j) {
        return calculator.calculate(totalCost, i, j, this);
    }

    @Override
    public String calculate(double totalCost, int i, int j, boolean c) {
        return "";
    }
}
