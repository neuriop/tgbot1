package calculator.launchpad;

import java.math.BigDecimal;

public class Launchpad {
    public static void main(String[] args) {
//        System.out.println(APRCounter("100", "0.05", 2));
//        System.out.println(APYCounter("100", "0.24", 2));
        System.out.println(launchpoolCounter("7000", "1000", "10"));
        System.out.println(launchpoolCounter("7000", "1000", "10", APRCounter("0.025", "5")));
    }

    private static double APRCounter(double fund, double percent, int years) {
        return fund * (1 + percent * years);
    }

    private static double APRCounter(String fund, String percent, int years) {
        BigDecimal f = new BigDecimal(fund);
        BigDecimal p = new BigDecimal(percent);
        BigDecimal y = new BigDecimal(years);
        return (f.multiply(p.multiply(y).add(new BigDecimal("1")))).doubleValue();
    }

    private static String APRCounter(String percent, String days) {
        BigDecimal p = new BigDecimal(percent);
        BigDecimal y = new BigDecimal(days);
        return (p.multiply(y).add(new BigDecimal("1"))).toString();
    }

    private static double APYCounter(double fund, double percent, int mPayments) {
        return fund * (1 + (Math.pow(1 + (percent / mPayments), mPayments) - 1));
    }

    private static double APYCounter(String fund, String percent, int mPayments) {
        BigDecimal f = new BigDecimal(fund);
        BigDecimal p = new BigDecimal(percent);
        BigDecimal m = new BigDecimal(mPayments);
        return (f.multiply(new BigDecimal("1").add((new BigDecimal("1").add(p.divide(m))).pow(mPayments).subtract(new BigDecimal("1"))))).doubleValue();
    }

    private static double launchpoolCounter(String prize, String totalSum, String fund){
        BigDecimal prz = new BigDecimal(prize);
        BigDecimal sum = new BigDecimal(totalSum);
        BigDecimal fun = new BigDecimal(fund);
//        BigDecimal apr = new BigDecimal(APR);
//        BigDecimal limit = new BigDecimal(individualLimit);
//        BigDecimal ptr = new BigDecimal(participants);
        return fun.divide(sum).multiply(prz).doubleValue();
    }

    private static String launchpoolCounter(String prize, String totalSum, String fund, String APR){
        BigDecimal prz = new BigDecimal(prize);
        BigDecimal sum = new BigDecimal(totalSum);
        BigDecimal fun = new BigDecimal(fund);
        BigDecimal apr = new BigDecimal(APR);
//        BigDecimal limit = new BigDecimal(individualLimit);
//        BigDecimal ptr = new BigDecimal(participants);

        return fun.divide(sum).multiply(prz).multiply(apr).toString();
    }

    public String launchpoolCalculate(String prize, String totalSum, String fund, String percent, String days){
        return launchpoolCounter(prize, totalSum, fund, APRCounter(percent, days));
    }

//    private String processString(String string){
//        String str =
//    }



}
// String APR, String individualLimit, int participants