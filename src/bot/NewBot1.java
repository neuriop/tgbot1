package bot;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewBot1 implements LongPollingSingleThreadUpdateConsumer {
    private TelegramClient telegramClient;
    private Map<Long, Conversation> chats = new HashMap<>();

    public NewBot1(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    @Override
    public void consume(List<Update> updates) {
        LongPollingSingleThreadUpdateConsumer.super.consume(updates);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() || update.hasCallbackQuery()) {
            long chat_id = update.getMessage().getChatId();
            if (update.getMessage().hasText())
                System.out.println(update.getMessage().getText());
            if (!chats.containsKey(chat_id))
                chats.put(chat_id, new Conversation(chat_id));
            sendMessage(chats.get(chat_id).processConversation(update));
        }
    }

    private void sendMessage(String text, long chat_id) {
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

    private void sendMessage(SendMessage message) {
        try {
            telegramClient.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}

