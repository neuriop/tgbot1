package calculator.credits.tables;

import calculator.credits.calculators.inputprocessing.CalculateInput;
import calculator.credits.calculators.inputprocessing.CalculateTable;

public class ToyotaCredit extends CreditTable {
    public ToyotaCredit() {
        calculator = new CalculateInput();
        percentTable = new double[][]{
                {3.49, 6.99, 8.99, 11.99, 11.99},
                {2.49, 5.99, 8.99, 11.99, 11.99},
                {1.49, 4.99, 7.49, 9.99, 9.99},
                {0.01, 3.99, 5.49, 9.99, 9.99},
                {0.01, 0.01, 4.99, 7.99, 7.99}};
        initialPayment = new double[]{0.3, 0.4, 0.5, 0.6, 0.7};
        monthList = new int[]{12, 24, 36, 48, 60};
        initialCommission = new double[]{2.99, 2.99, 2.99, 0, 0};
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment) {
        return calculator.calculate(totalCost, initialPayment, monthlyPayment, new ToyotaCredit());
    }

    @Override
    public String calculate(double totalCost, int i, int j, boolean c) {
        return "";
    }

    @Override
    public String calculate(double totalCost, int i, int j) {
        return calculator.calculate(totalCost, i, j, this);
    }
}
