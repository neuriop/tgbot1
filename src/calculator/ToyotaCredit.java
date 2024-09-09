package calculator;

public class ToyotaCredit extends CreditTable {
    public ToyotaCredit() {
        percentTable = new double[][]{
                {3.49, 6.99, 8.99, 11.99, 11.99},
                {2.49, 5.99, 8.99, 11.99, 11.99},
                {1.49, 4.99, 7.49, 9.99, 9.99},
                {0.01, 3.99, 5.49, 9.99, 9.99},
                {0.01, 0.01, 4.99, 7.99, 7.99}};
        initialPayment = new double[]{0.3, 0.4, 0.5, 0.6, 0.7};
        monthList = new int[]{12, 24, 36, 48, 60};
        initialCommission = new double[]{2.99, 2.99, 2.99, 0, 0};
        for (int i : extraCondition = new int[5]) {
            extraCondition[i] = monthList[i];
        }
        alternatePercentTable = new double[][]{};
    }
}
