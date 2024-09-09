import bot.NewBot1;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner tokenInput = new Scanner(System.in);

        String botToken = "Insert Your Token Here";
        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            botsApplication.registerBot(botToken, new NewBot1(botToken));
            System.out.println("MyAmazingBot successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}