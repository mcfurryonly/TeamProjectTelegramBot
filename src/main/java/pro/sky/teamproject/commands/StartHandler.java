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


    public StartHandler(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void handle(Update update) {
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют собак").callbackData("dog");
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют кошек").callbackData("cat");
//        InlineKeyboardButton[] arrayOfButtons = new InlineKeyboardButton[]{
//                dogButton, catButton};
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(dogButton, catButton);
        SendMessage sendMessage = new SendMessage(update.message().chat().id(),
                "Добро пожаловать в приложение")
                .replyMarkup(markup);
        bot.execute(sendMessage);
    }

    @Override
    public boolean isSuitable(Update update) {
        return Optional.of(update)
                .map(Update::message)
                .map(Message::text)
                .map(el -> el.equals(START))
                .orElse(false);
    }

//    @Override
//    public boolean isSuitable(Update update) {
//        if (update != null && update.message() != null && update.message().text() != null) {
//            return update.message().text().equals(START);
//        }
//        return false;
//    }
}
