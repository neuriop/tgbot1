package checks;

public class Are3Args implements Check {
    @Override
    public String errorMessage() {
        return "Provided incorrect amount of arguments.";
    }

    @Override
    public boolean update(String[] strings) {
        return strings.length == 3;
    }
}
