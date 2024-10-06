package bot;

import bot.commands.Carcredit;
import bot.commands.Command;
import calculator.launchpad.LPChecker;
import calculator.credits.checks.CarChecker;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Conversation {
    private final String chat_id;
    private int steps = 0;
    private List<String> inputs = new ArrayList<>();
    private String lastCommand;
    private Command lastCommand2;

    public Conversation(long chat_id){
        this.chat_id = String.valueOf(chat_id);
    }

    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }

    public SendMessage processConversation(Update update){
        if (lastCommand != null){
            return processLastCommand(update);
        } else if (update.getMessage().hasText()){
            return processText(update);
        }
        return new SendMessage(chat_id, "Message cannot be processed");
    }

    private SendMessage processLastCommand(Update update){
        String input = update.getMessage().getText();
        String result;
        if (Objects.equals(lastCommand, "carcredit")){
            if (lastCommand2 == null)
                lastCommand2 = new Carcredit(chat_id, this);
            return lastCommand2.processCommand(update);
        } else if (Objects.equals(lastCommand, "launchpool")){
            result = processLPChecker(input);
        } else result = "Last command is unknown";
        return new SendMessage(chat_id, result);
    }

    private SendMessage processText(Update update) {
        steps = 0;
        inputs.clear();
        String input = update.getMessage().getText();
        String result;
        if (input.contains("start") || input.contains("help")) {
            result = "Enter command:" +
                    "/start - show this message" +
                    "/help - show this message" +
                    "/carcredit - calculate car credit" +
                    "/launchpool - calculate launchpool";
        } else if (input.equals("/carcredit")) {
            lastCommand = "carcredit";
            result = "Enter total car price";
        } else if (input.equals("/launchpool")) {
            lastCommand = "launchpool";
            result = "Enter total pool prize";
        } else result = "Unknown command";
        return new SendMessage(chat_id, result);
    }


    @Nullable
    private String setCommand(String input) {
        steps = 0;
        inputs.clear();
        if (input.contains("start") || input.contains("help")) {
            return "Enter command:\n/carcredit - calculate car credit\n/launchpool - calculate launchpool";
        } else if (input.equals("/carcredit")) {
            lastCommand = "carcredit";
            return "Enter total car price";
        } else if (input.equals("/launchpool")) {
            lastCommand = "launchpool";
            return "Enter total pool prize";
        }
        return null;
    }

    @Nullable
    private static String stringValidator(String[] arg) {
        if (!new calculator.launchpad.IsValidNum().update(arg)) {
            return "Invalid number.";
        }
        return null;
    }

    @Nullable
    private static String stringValidator2(String[] arg) {
        if (!new calculator.credits.checks.IsValidNum().update(arg)) {
            return "Invalid number.";
        }
        return null;
    }



    private String processCarCredit1(String input) {
        String[] arg = {input};
        switch (steps) {
            case 0:
                if (stringValidator2(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter initial payment amount you are able to make";
            case 1:
                if (stringValidator2(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps++;
                return "Enter monthly payment you are able to make";
            case 2:
                if (stringValidator2(arg) != null)
                    return stringValidator(arg);
                inputs.add(input);
                steps = 0;
                StringBuilder res = new StringBuilder();
                for (String in : inputs) {
                    res.append(in).append("\n");
                }
                lastCommand = null;
                inputs.clear();
                System.out.println(new CarChecker().processString(res.toString()));
                return new CarChecker().processString(res.toString());
            default:
                return "something went wrong";
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
                System.out.println(new LPChecker().processString(res.toString()));
                return new LPChecker().processString(res.toString());
            default:
                return "something went wrong";
        }
    }
}
