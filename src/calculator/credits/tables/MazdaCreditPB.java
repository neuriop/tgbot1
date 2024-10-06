package calculator.credits.tables;

import calculator.credits.calculators.inputprocessing.CalculateInput;
import calculator.credits.calculators.inputprocessing.CalculateMazda;

public class MazdaCreditPB extends CreditTable {
    public MazdaCreditPB() {
        calculator = new CalculateInput();
        percentTable = new double[][]{
                {6.5, 21.9},
                {4.9, 21.9},
                {2.9, 21.9},
                {0.01, 21.9},
                {0.01, 14, 9}};
        initialPayment = new double[]{0.2, 0.3, 0.4, 0.5, 0.6};
        monthList = new int[]{24, 36};
        initialCommission = new double[]{0, 0};
    }

    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment) {
        return calculator.calculate(totalCost, initialPayment, monthlyPayment, new MazdaCreditPB());
    }

    @Override // no logic currently available
    public String calculate(double totalCost, int i, int j) {
        return "";
    }
}
