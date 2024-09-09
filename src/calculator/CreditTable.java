package calculator;

public abstract class CreditTable {
    protected double[][] percentTable;
    protected double[] initialPayment;
    protected int[] monthList;
    protected double[] initialCommission;
    protected int[] extraCondition;
    protected double[][] alternatePercentTable;

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

    public int[] getExtraCondition() {
        return extraCondition;
    }

    public double[][] getAlternatePercentTable() {
        return alternatePercentTable;
    }
}
