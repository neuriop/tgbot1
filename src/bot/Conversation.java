package bot;

import calculator.launchpad.LPChecker;
import checks.CarChecker;
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
    private String chat_id;
    private int steps = 0;
    private List<String> inputs = new ArrayList<>();
    private String lastCommand;

    public Conversation(long chat_id){
        this.chat_id = String.valueOf(chat_id);
    }

    public SendMessage processConversation(Update update){
        if (lastCommand != null){
            return processLastCommand(update);
        } else if (update.getMessage().hasText()){
            return processText(update);
        } else if (update.hasCallbackQuery()){
            return processCallBackQuery(update);
        }
        return new SendMessage(chat_id, "Message cannot be processed");
    }

    private SendMessage processLastCommand(Update update){
        String input = update.getMessage().getText();
        String result;
        if (Objects.equals(lastCommand, "carcredit")){
            result = processCarCredit1(input);
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
            result = "Enter command:\n/carcredit - calculate car credit\n/launchpool - calculate launchpool";
        } else if (input.equals("/carcredit")) {
            lastCommand = "carcredit";
            result = "Enter total car price";
        } else if (input.equals("/launchpool")) {
            lastCommand = "launchpool";
            result = "Enter total pool prize";
        } else result = "Unknown command";
        return new SendMessage(chat_id, result);
    }

    public SendMessage processCallBackQuery(Update update) {
        return null;
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

    @Nullable
    private static String stringValidator(String[] arg) {
        if (!new calculator.launchpad.IsValidNum().update(arg)) {
            return "Invalid number.";
        }
        return null;
    }

    @Nullable
    private static String stringValidator2(String[] arg) {
        if (!new checks.IsValidNum().update(arg)) {
            return "Invalid number.";
        }
        return null;
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callbackData);
        return button;
    }

    private SendMessage sendOptions() {
        InlineKeyboardMarkup markup;
        InlineKeyboardRow inlineKeyboardRow = new InlineKeyboardRow();
        List<InlineKeyboardRow> rows = new ArrayList<>();

        List<InlineKeyboardButton> row30 = new ArrayList<>();
        row30.add(createButton("30% - 12m/3.49%", "0 0"));
        row30.add(createButton("30% - 24m/6.99%", "0 1"));
        row30.add(createButton("30% - 36m/8.99%", "0 2"));
        row30.add(createButton("30% - 48m/11.99%", "0 3"));
        row30.add(createButton("30% - 60m/11.99%", "0 4"));
        rows.add(new InlineKeyboardRow(row30));

        List<InlineKeyboardButton> row40 = new ArrayList<>();
        row40.add(createButton("40% - 12m/2.49%", "1 0"));
        row40.add(createButton("40% - 24m/5.99%", "1 1"));
        row40.add(createButton("40% - 36m/8.99%", "1 2"));
        row40.add(createButton("40% - 48m/11.99%", "1 3"));
        row40.add(createButton("40% - 60m/11.99%", "1 4"));
        rows.add(new InlineKeyboardRow(row40));

        List<InlineKeyboardButton> row50 = new ArrayList<>();
        row50.add(createButton("50% - 12m/1.49%", "2 0"));
        row50.add(createButton("50% - 24m/4.99%", "2 1"));
        row50.add(createButton("50% - 36m/7.49%", "2 2"));
        row50.add(createButton("50% - 48m/9.99%", "2 3"));
        row50.add(createButton("50% - 60m/9.99%", "2 4"));
        rows.add(new InlineKeyboardRow(row50));

        List<InlineKeyboardButton> row60 = new ArrayList<>();
        row60.add(createButton("60% - 12m/0.01%", "3 0"));
        row60.add(createButton("60% - 24m/3.99%", "3 1"));
        row60.add(createButton("60% - 36m/5.49%", "3 2"));
        row60.add(createButton("60% - 48m/9.99%", "3 3"));
        row60.add(createButton("60% - 60m/9.99%", "3 4"));
        rows.add(new InlineKeyboardRow(row60));

        List<InlineKeyboardButton> row70 = new ArrayList<>();
        row70.add(createButton("70% - 12m/0.01%", "4 0"));
        row70.add(createButton("70% - 24m/0.01%", "4 1"));
        row70.add(createButton("70% - 36m/4.99%", "4 2"));
        row70.add(createButton("70% - 48m/7.99%", "4 3"));
        row70.add(createButton("70% - 60m/7.99%", "4 4"));
        rows.add(new InlineKeyboardRow(row70));

        markup = new InlineKeyboardMarkup(rows);

        SendMessage message = new SendMessage(chat_id, "Select your prepayment percent and loan term");
        message.setReplyMarkup(markup);
        return message;
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
}
