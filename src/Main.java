import bot.NewBot1;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String botToken = new Scanner(System.in).nextLine();
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken, new NewBot1(botToken));
            System.out.println("NewBot1 successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}