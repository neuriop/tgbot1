package calculator.credits.checks;

public interface Check {
    boolean update(String[] strings);
    String errorMessage();
}
