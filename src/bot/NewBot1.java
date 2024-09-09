package bot;

import checks.Checker;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import java.util.List;

public class NewBot1 implements LongPollingSingleThreadUpdateConsumer {
    private TelegramClient telegramClient;

    public NewBot1(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    @Override
    public void consume(List<Update> updates) {
        LongPollingSingleThreadUpdateConsumer.super.consume(updates);
    }

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            System.out.println(update.getMessage().getText());
            Checker checker = new Checker();
            String response = checker.processString(update.getMessage().getText());
            sendMessage(response, chat_id);
        }
    }

    

    private void sendMessage(String text, long chat_id){
        SendMessage message = SendMessage
                .builder()
                .chatId(chat_id)
                .text(text)
                .build();
        try {
            telegramClient.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
