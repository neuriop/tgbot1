package calculator.credits.brands;

import calculator.credits.tables.RenaultCreditPB;

import java.util.HashMap;

public class Renault extends Brand{
    public Renault(){
        credits = new HashMap<>();
        credits.put("privatbank", new RenaultCreditPB());
    }
}
