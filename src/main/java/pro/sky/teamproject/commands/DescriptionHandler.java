package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public class DescriptionHandler implements ExtendedHandler {

    private final String INFO = "/info";
    private final String TAKE = "/take";
    private final String REPORT = "/report";
    private final String VOLUNTEER = "/volunteer";
    private final TelegramBot bot;

    public DescriptionHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void handle(Update update, String userChoice) {
        Message message = update.message();
        if (message != null && message.text() != null) {
            String text = message.text();
            Long chatId = message.chat().id();
            switch (text) {
                case INFO:
                    if (userChoice.equals("Dog")) {
                        bot.execute(new SendMessage(chatId, "Информация о собаке"));
                    } else if (userChoice.equals("Cat")) {
                        bot.execute(new SendMessage(chatId, "Информация о кошке"));
                    }
                    break;
                case TAKE:
                    if (userChoice.equals("Dog")) {
                        bot.execute(new SendMessage(chatId, "Вы берете собаку из приюта"));
                    } else if (userChoice.equals("Cat")) {
                        bot.execute(new SendMessage(chatId, "Вы берете кошку из приюта"));
                    }
                    break;
                case REPORT:
                    if (userChoice.equals("Dog")) {
                        bot.execute(new SendMessage(chatId, "Отчет о собаке"));
                    } else if (userChoice.equals("Cat")) {
                        bot.execute(new SendMessage(chatId, "Отчет о кошке"));
                    }
                    break;
                case VOLUNTEER:
                    if (userChoice.equals("Dog")) {
                        bot.execute(new SendMessage(chatId, "Вот номер волонтера для собак +7 *** ***"));
                    } else if (userChoice.equals("Cat")) {
                        bot.execute(new SendMessage(chatId, "Вот номер волонтера для кошек +7 *** ***"));
                    }
                    break;
            }
        }
    }

    @Override
    public boolean isSuitable(Update update) {
        Message message = update.message();
        return message != null && message.text() != null &&
                (message.text().equals(INFO) || message.text().equals(TAKE) ||
                        message.text().equals(REPORT) || message.text().equals(VOLUNTEER));
    }
}
