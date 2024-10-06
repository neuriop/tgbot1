package calculator.credits.brands;

import calculator.credits.tables.CreditTable;

import java.util.Map;

public abstract class Brand {
    protected Map<String, CreditTable> credits;

    public CreditTable getCredit(String bank) {
        return credits.get(bank);
    }

    public Map<String, CreditTable> getCreditMaps() {
        return credits;
    }
}
