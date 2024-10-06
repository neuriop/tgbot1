package calculator.credits.tables;

import calculator.credits.calculators.CreditCalculator;

public abstract class CreditTable {
    protected CreditCalculator calculator;
    protected double[][] percentTable;
    protected double[] initialPayment;
    protected int[] monthList;
    protected double[] initialCommission;

    public double[][] getPercentTable() {
        return percentTable;
    }

    public double[] getInitialPayment() {
        return initialPayment;
    }

    public int[] getMonthList() {
        return monthList;
    }

    public double[] getInitialCommission() {
        return initialCommission;
    }

    public abstract String calculate(double totalCost, double initialPayment, double monthlyPayment);

    public abstract String calculate(double totalCost, int i, int j);
}
