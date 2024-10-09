package calculator.credits.brands;

import calculator.credits.tables.MazdaCreditOB;
import calculator.credits.tables.MazdaCreditPB;

import java.util.HashMap;

public class Mazda extends Brand{
    public Mazda(){
        credits = new HashMap<>();
        credits.put("privatbank", new MazdaCreditPB());
        credits.put("oschadbank", new MazdaCreditOB());
//        credits.put("agricol", new MazdaCreditAC());
//        credits.put("agricol-multistep", new MazdaCreditAM());
    }
}
