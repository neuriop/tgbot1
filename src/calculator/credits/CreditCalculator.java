package calculator.credits;


public abstract class CreditCalculator {

    public abstract String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit);

    protected boolean lowDiffNextCounter(double totalCost, double monthlyPay, CreditTable credit, int lowDiffPrev, int lowDiffNext, int i) {
        return Math.abs(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]) - monthlyPay) > Math.abs(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev][i]) - monthlyPay);
    }

    protected boolean lowDiffPrevCounter(double totalCost, double initialPay, CreditTable credit, int lowDiffPrev, int i) {
        return Math.abs(initialPay - initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev])) > Math.abs(initialPay - initialPayCounter(totalCost, credit.getInitialPayment()[i]));
    }

    protected double initialPayCounter(double totalCost, double initialPayPercent) {
        return Math.ceil(totalCost * initialPayPercent);
    }

    protected double restPayCounter(double totalCost, double initialPayPercent) {
        return Math.ceil(totalCost - (totalCost * initialPayPercent));
    }

    protected double monthlyPayCounter(double totalCost, double initialPayPercent, int month, double percent) {
        return restPayCounter(totalCost, initialPayPercent) / month * (percent / 100) + restPayCounter(totalCost, initialPayPercent) / month;
    }

    protected double totalCostCounter(double totalCost, CreditTable credit, int lowDiffPrev, int lowDiffNext) {
        return initialPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev]) + Math.ceil(monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext])) * credit.getMonthList()[lowDiffNext] + monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev], credit.getMonthList()[lowDiffNext], credit.getPercentTable()[lowDiffPrev][lowDiffNext]) * (credit.getInitialCommission()[lowDiffNext] / 100);
    }

    protected double totalCostCounter(double totalCost, double initialPay, CreditTable credit) {
        double result = 0;
        for (int i = 0; i < credit.getMonthList().length; i++) {
            result += credit.getMonthList()[i] * monthlyPayCounter(totalCost, credit.getInitialPayment()[lowDiffPrev(totalCost, initialPay, credit)], credit.getMonthList()[i], credit.getPercentTable()[lowDiffPrev(totalCost, initialPay, credit)][i]);
        }
        return result;
    }

    protected int lowDiffPrev(double totalCost, double initialPay, CreditTable credit) {
        int lowDiffPrev = 0;
        for (int i = 1; i < credit.getMonthList().length; i++) {
            if (lowDiffPrevCounter(totalCost, initialPay, credit, lowDiffPrev, i)) {
                lowDiffPrev = i;
            }
        }
        return lowDiffPrev;
    }

    protected int lowDiffNext(double totalCost, double initialPay, double monthlyPay, CreditTable credit) {
        int lowDiffNext = 0;
        for (int i = 1; i < credit.getMonthList().length; i++) {
            if (lowDiffNextCounter(totalCost, monthlyPay, credit, lowDiffPrev(totalCost, initialPay, credit), lowDiffNext, i)) {
                lowDiffNext = i;
            }
        }
        return lowDiffNext;
    }

}

