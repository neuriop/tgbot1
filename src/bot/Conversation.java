package bot;

import bot.commands.Carcredit;
import bot.commands.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Conversation {
    private final String chat_id;
    private Command lastCommand;

    public Conversation(long chat_id) {
        this.chat_id = String.valueOf(chat_id);
    }

    public void setLastCommandToNull() {
        this.lastCommand = null;
    }

    public SendMessage processConversation(Update update) {
        if (update.hasMessage()){
            if (update.getMessage().getText().equals("/cancel")){
                setLastCommandToNull();
                return new SendMessage(chat_id, "Command canceled");
            }
        }
        if (lastCommand != null) {
            return processLastCommand(update);
        } else if (update.hasMessage()) {
            return processText(update);
        }
        return new SendMessage(chat_id, "Message cannot be processed");
    }

    private SendMessage processLastCommand(Update update) {
        if (lastCommand.getClass().equals(Carcredit.class)) {
            return lastCommand.processCommand(update);
        } else return new SendMessage(chat_id, "Last command is unknown");
    }

    private SendMessage processText(Update update) {
        String input = update.getMessage().getText();
        String result;
        if (input.contains("start") || input.contains("help")) {
            result = "Enter command:" +
                    "\n/start - show this message" +
                    "\n/help - show this message" +
                    "\n/carcredit - calculate car credit" +
                    "\nnew commands will be added in future";
            return new SendMessage(chat_id, result);
        } else if (input.equals("/carcredit")) {
            lastCommand = new Carcredit(chat_id, this);
            return lastCommand.processCommand(update);
        }
        return new SendMessage(chat_id, "Unknown command");
    }
}
