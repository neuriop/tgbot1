package calculator.credits;

public class CalculateInput extends CreditCalculator {
    @Override
    public String calculate(double totalCost, double initialPayment, double monthlyPayment, CreditTable credit) {
        StringBuilder result = new StringBuilder();
        result.append("Calculated result for ").append(credit.getClass().getSimpleName());
        result.append("Total car cost: ").append(totalCost);
        result.append("Initial payment: ").append(credit.getInitialPayment()[(int) monthlyPayment] * 100).append("% - ").append(totalCost * credit.getInitialPayment()[(int) monthlyPayment]);
        result.append("Monthly payment: ").append((totalCost - (totalCost * credit.getInitialPayment()[(int) monthlyPayment])) / credit.getMonthList()[(int) monthlyPayment] * (credit.getPercentTable()[(int) monthlyPayment][(int) initialPayment] / 100 + 1));
        result.append("Initial commission: ").append(credit.getInitialCommission()[(int) monthlyPayment]).append("% - ").append(totalCost*credit.getInitialPayment()[(int) monthlyPayment]*(credit.getInitialCommission()[(int) monthlyPayment]/100));
        result.append("Total credit cost: ").append(((totalCost - (totalCost * credit.getInitialPayment()[(int) monthlyPayment])) / credit.getMonthList()[(int) monthlyPayment] * (credit.getPercentTable()[(int) monthlyPayment][(int) initialPayment] / 100 + 1))*credit.getMonthList()[(int) monthlyPayment]+(totalCost*credit.getInitialPayment()[(int) monthlyPayment]*(credit.getInitialCommission()[(int) monthlyPayment]/100)));

        return result.toString();
    }
}
