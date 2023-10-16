package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartHandler implements Handler {

    private final String START = "/start";
    private final TelegramBot bot;

    @Autowired
    private DescriptionHandler descriptionHandler;

    public StartHandler(TelegramBot bot) {
        this.bot = bot;
    }

    /**
     * @param update creates a buttons and sends a message
     */
    public void handle(Update update) {
        String DOG = "Dog";
        String CAT = "Cat";
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют собак").callbackData(DOG);
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют кошек").callbackData(CAT);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(dogButton, catButton);

        if (update.message() != null) {
            long chatId = update.message().chat().id();
            SendMessage sendMessage = new SendMessage(chatId,
                    "Добро пожаловать в приложение")
                    .replyMarkup(markup);
            bot.execute(sendMessage);
        }

        if (update.callbackQuery() != null) {
            var callbackData = update.callbackQuery().data();
            long chatId = update.callbackQuery().message().chat().id();

            if (callbackData.equals(DOG)) {
                bot.execute(new SendMessage(chatId, "Собака"));
                descriptionHandler.handle(update, DOG);
            } else if (callbackData.equals(CAT)) {
                bot.execute(new SendMessage(chatId, "Кот"));
                descriptionHandler.handle(update, CAT);
            } else {
                bot.execute(new SendMessage(chatId, "Что то пошло не так"));
            }
        }
    }

    @Override
    public boolean isSuitable(Update update) {
        return Optional.of(update)
                .map(Update::message)
                .map(Message::text)
                .map(el -> el.equals(START))
                .orElse(false);
    }
}
