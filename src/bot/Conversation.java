package bot;

import calculator.launchpad.IsValidNum;
import calculator.launchpad.LPChecker;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conversation {
    private int steps = 0;
    private List<String> inputs = new ArrayList<>();
    private String lastCommand;

    public String processConversation(String input) {
        if (Objects.equals(lastCommand, "carcredit")){
            return processCarCredit(input);
        } else if (Objects.equals(lastCommand, "launchpool")) {
            return processLPChecker(input);
        } else {
            steps = 0;
            inputs.clear();
            if (input.contains("start") || input.contains("help")) {
                return "Enter command:\n/carcredit - calculate car credit\n/launchpool - calculate launchpool";
            } else if (input.equals("/carcredit")) {
                lastCommand = "carcredit";
            } else if (input.equals("/launchpool")) {
                lastCommand = "launchpool";
                return "Enter total pool prize";
            }
            return "Unknown input";
        }
    }

    private String processLPChecker(String input) {
        String[] arg = {input};
        switch (steps) {
            case 0:
                if (stringValidator(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter full pool contribution";
            case 1:
                if (stringValidator(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter personal fund";
            case 2:
                if (stringValidator(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter APR percent";
            case 3:
                if (stringValidator(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter pool duration (days)";
            case 4:
                if (stringValidator(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps = 0;
                StringBuilder res = new StringBuilder();
                for (String in : inputs) {
                    res.append(in).append("\n");
                }
                lastCommand = null;
                inputs.clear();
                return new LPChecker().processString(res.toString());
            default:
                return "something went wrong";
        }
    }

    @Nullable
    private static String stringValidator(String[] arg) {
        if (!new IsValidNum().update(arg)){
            return "Invalid number.";
        }
        return null;
    }

    private String processCarCredit(String input) {
        return "not implemented yet";
    }
}
