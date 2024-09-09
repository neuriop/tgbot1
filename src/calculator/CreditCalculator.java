package calculator;

import java.util.ArrayList;
import java.util.List;

public class CreditCalculator {
    private List<CreditTable> credits = new ArrayList<>();


    public CreditCalculator(){
        credits.add(new ToyotaCredit());
    }

    public String countCredit(double totalCost, double initialPayment, double monthlyPayment) {
        String result = "";
        for (CreditTable credit : credits) {
            result += creditCalculator(totalCost, initialPayment, monthlyPayment, credit) + "\n";
        }
        return result;
    }

    private String creditCalculator(double totalCost, double initialPay, double monthlyPay, CreditTable credit) {
        String result = "";
        int lowDiffPrev = 0;
        int lowDiffNext = 0;
        for (int i = 1; i < credit.getMonthList().length; i++) {
            if (lowDiffPrevCounter(totalCost, initialPay, credit, lowDiffPrev, i)) {
                lowDiffPrev = i;
            }
        }
        if (initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]) != initialPay) {
            result += "Initial payment of " + initialPay + " is not available. The closest available payment is " + initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]);
        }
        result += "\nInitial\t\tMonthly payment";
        result += "\npayment";
        for (int i : credit.getMonthList()) {
            result += "\t\t" + i;
        }
        result += "\n" + initialPay + "\t";
        for (int i = 1; i < credit.getMonthList().length; i++) {
            if (lowDiffNextCounter(totalCost, monthlyPay, credit, lowDiffPrev, lowDiffNext, i)){
                lowDiffNext = i;
            }
        }
        for (int i = 0; i < credit.getPercentTable()[lowDiffPrev].length; i++) {
            if (lowDiffNext == i) {
                result += "\t" + "\u001B[32m" + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i])) + "\u001B[0m";
            }
            else result += "\t" + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i]));
        }
        result += "\nThe closest monthly payment to " + monthlyPay + " you can do is: " + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]));
        result += "\nTotal cost of the credit is: " + totalCostCounter(totalCost, credit, lowDiffPrev, lowDiffNext);
        return result;
    }

    private double totalCostCounter(double totalCost, CreditTable credit, int lowDiffPrev, int lowDiffNext) {
        return initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]) + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext])) * credit.getMonthList()[lowDiffNext] + monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]) * (credit.getInitialCommission()[lowDiffNext] / 100);
    }

    private boolean lowDiffNextCounter(double totalCost, double monthlyPay, CreditTable credit, int lowDiffPrev, int lowDiffNext, int i) {
        return Math.abs(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]) - monthlyPay) > Math.abs(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i]) - monthlyPay);
    }

    private boolean lowDiffPrevCounter(double totalCost, double initialPay, CreditTable credit, int lowDiffPrev, int i) {
        return Math.abs(initialPay - initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev])) > Math.abs(initialPay - initialPayCounter(totalCost, credit.getInitialPayment()[i]));
    }

    private double initialPayCounter(double totalCost, double initialPayPercent) {
        return Math.ceil(totalCost * initialPayPercent);
    }

    private double restPayCounter(double totalCost, double initialPayPercent) {
        return Math.ceil(totalCost - (totalCost * initialPayPercent));
    }

    private double monthlyPayCounter(double totalCost, double initialPayPercent, int month, double percent) {
        return restPayCounter(totalCost, initialPayPercent) / month * (percent / 100) + restPayCounter(totalCost, initialPayPercent) / month;
    }



}

