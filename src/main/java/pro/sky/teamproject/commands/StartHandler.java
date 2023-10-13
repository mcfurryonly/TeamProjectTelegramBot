package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StartHandler implements Handler {

    private final String START = "/start";
    private final TelegramBot bot;
    private DescriptionHandler descriptionHandler;

    public StartHandler(TelegramBot bot) {
        this.bot = bot;
    }

    /**
     * @param update creates a buttons and sends a message
     */
    public void handle(Update update) {
        String DOG = "Dog";
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют собак").callbackData(DOG);
        String CAT = "Cat";
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют кошек").callbackData(CAT);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(dogButton, catButton);
        SendMessage sendMessage = new SendMessage(update.message().chat().id(),
                "Добро пожаловать в приложение")
                .replyMarkup(markup);
        bot.execute(sendMessage);
        if (update.message().text().equals(DOG)) {
            descriptionHandler.handle(update, DOG);
        } else if (update.message().text().equals(CAT)) {
            descriptionHandler.handle(update, CAT);
        }else {
            bot.execute(new SendMessage(update.message().chat().id(), "Что то пошло не так"));
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

///    @Override
///    public boolean isSuitable(Update update) {
//        if (update != null && update.message() != null && update.message().text() != null) {
//            return update.message().text().equals(START);
//        }
//        return false;
//    }
}
