package calculator.credits.brands;

import calculator.credits.tables.ToyotaCredit;

import java.util.HashMap;

public class Toyota extends Brand{
    public Toyota(){
        credits = new HashMap<>();
        credits.put("default", new ToyotaCredit());
    }
}
