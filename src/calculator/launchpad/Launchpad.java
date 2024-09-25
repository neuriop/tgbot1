package calculator.launchpad;

import java.math.BigDecimal;

public class Launchpad {

    private static String APRCounter(String percent, String days) {
        BigDecimal p = new BigDecimal(percent);
        BigDecimal y = new BigDecimal(days);
        return (p.multiply(y).add(new BigDecimal("1"))).toString();
    }


    private static double APYCounter(String fund, String percent, int mPayments) {
        BigDecimal f = new BigDecimal(fund);
        BigDecimal p = new BigDecimal(percent);
        BigDecimal m = new BigDecimal(mPayments);
        return (f.multiply(new BigDecimal("1").add((new BigDecimal("1").add(p.divide(m))).pow(mPayments).subtract(new BigDecimal("1"))))).doubleValue();
    }


    private String launchpoolCounter(String prize, String totalSum, String fund, String APR){
        BigDecimal prz = new BigDecimal(prize);
        BigDecimal sum = new BigDecimal(totalSum);
        BigDecimal fun = new BigDecimal(fund);
        BigDecimal apr = new BigDecimal(APR);
        return fun.divide(sum).multiply(prz).multiply(apr).toString();
    }

    public String launchpoolCalculate(String prize, String totalSum, String fund, String percent, String days){
        return launchpoolCounter(prize, totalSum, fund, APRCounter(percent, days));
    }

}
