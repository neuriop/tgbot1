package bot.commands;

import bot.Conversation;
import calculator.credits.brands.Brand;
import calculator.credits.brands.Mazda;
import calculator.credits.brands.Toyota;
import calculator.credits.tables.CreditTable;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carcredit implements Command {
    private final String chat_id;
    private CreditTable credit;
    private Brand brand;
    private Map<String, Brand> brands = new HashMap<>();
    private Conversation conversation;
    private int step = 0;
    private double totalCost;

    public Carcredit(String chat_id, Conversation conversation) {
        this.chat_id = chat_id;
        this.conversation = conversation;
        brands.put(Toyota.class.getSimpleName(), new Toyota());
        brands.put(Mazda.class.getSimpleName(), new Mazda());
    }

    @Override
    public SendMessage processCommand(Update update) {
        SendMessage message;
        String result = "";
        switch (step) {
            case 0: {
                step++;
                conversation.setLastCommand("carcredit");
                return new SendMessage(chat_id, "Enter car price");
            }
            case 1: {
                totalCost = doubleValidator(update.getMessage().toString());
                step++;
                return sendBrandButtons();
            }
            case 2:{
                if (update.hasCallbackQuery()) {
                    step++;
                    brand = brands.get(update.getCallbackQuery().getData());
                    return sendBankButtons(brands.get(update.getCallbackQuery().getData()));
                } else {
                    return new SendMessage(chat_id, "Press the button to select brand");
                }
            }
            case 3: {
                if (update.hasCallbackQuery()){
                    step++;
                    credit = brand.getCredit(update.getCallbackQuery().getData());
                    return sendCreditButtons(credit);
                } else {
                    return new SendMessage(chat_id, "Press the button to select brand");
                }
            }
            case 4: {
                if (update.hasCallbackQuery()){
                    String[] ij = update.getCallbackQuery().getData().split(" ");
                    return new SendMessage(chat_id, credit.calculate(totalCost, Integer.parseInt(ij[0]), Integer.parseInt(ij[1])) + "\nType /cancel to cancel this command");
                } else {
                    step = 0;
                    conversation.setLastCommand(null);
                    return new SendMessage(chat_id, "Command canceled");
                }
            }
        }
        assert result != null;
        message = new SendMessage(chat_id, result);
        return message;
    }


    @Nullable
    private static String stringValidator(String[] arg) {
        if (!new calculator.credits.checks.IsValidNum().update(arg)) {
            return "Invalid number.";
        }
        return null;
    }

    private double doubleValidator(String str){
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callbackData);
        return button;
    }

    private SendMessage sendBrandButtons() {
        InlineKeyboardMarkup markup;
        List<InlineKeyboardRow> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String s : brands.keySet()) {
            row.add(createButton(s, s));
        }
        rows.add(new InlineKeyboardRow(row));
        markup = new InlineKeyboardMarkup(rows);
        SendMessage message = new SendMessage(chat_id, "Select brand of the car");
        message.setReplyMarkup(markup);
        return message;
    }

    private SendMessage sendBankButtons(Brand brand) {
        InlineKeyboardMarkup markup;
        List<InlineKeyboardRow> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        for (String s : brand.getCreditMaps().keySet()) {
            row.add(createButton(s, s));
        }
        rows.add(new InlineKeyboardRow(row));
        markup = new InlineKeyboardMarkup(rows);
        SendMessage message = new SendMessage(chat_id, "Select bank credit conditions");
        message.setReplyMarkup(markup);
        return message;
    }

    private SendMessage sendCreditButtons(CreditTable credit) {
        InlineKeyboardMarkup markup;
        List<InlineKeyboardRow> rows = new ArrayList<>();
        for (int i = 0; i < credit.getInitialPayment().length; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < credit.getPercentTable()[i].length; j++) {
                row.add(createButton(credit.getInitialPayment()[i] * 10 + "% - "
                                + credit.getMonthList()[j] + "m/" + credit.getPercentTable()[i][j] + "%",
                        i + " " + j));
            }
            rows.add(new InlineKeyboardRow(row));
        }

        markup = new InlineKeyboardMarkup(rows);
        SendMessage message = new SendMessage(chat_id, "Select your prepayment percent and loan term");
        message.setReplyMarkup(markup);
        return message;
    }


}
