package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import com.pengrad.telegrambot.model.CallbackQuery;


import java.util.Objects;
import java.util.Optional;

@Component
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

        InlineKeyboardButton infoButton = new InlineKeyboardButton("Инфо").callbackData(INFO);
        InlineKeyboardButton takeButton = new InlineKeyboardButton("Взять").callbackData(TAKE);
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Отчет").callbackData(REPORT);
        InlineKeyboardButton volunteerButton = new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(infoButton,takeButton,reportButton,volunteerButton);

            long chatId = update.callbackQuery().message().chat().id();
            SendMessage sendMessage = new SendMessage(chatId,
                    "Выберите действие")
                    .replyMarkup(markup);
            bot.execute(sendMessage);
        if (update.callbackQuery() != null) {
            var callbackData = update.callbackQuery().data();

            if (Objects.equals(userChoice, "Dog")){
                assert callbackData != null;
                if (callbackData.equals(INFO)){ //Каллбекнеться Dog нужно /info
                    bot.execute(new SendMessage(chatId, "Тут будет инфо о собаке"));
                }
                // Добавьте здесь обработку других команд для "Dog"
            } else if (Objects.equals(userChoice, "Cat")) {
                assert callbackData != null;
                if (callbackData.equals(INFO)){
                    SendMessage sendMessage1 = new SendMessage(chatId, "Тут будет инфо о кошке");
                    bot.execute(sendMessage1);
                }
                // Добавьте здесь обработку других команд для "Cat"
            }
        }


    }

    @Override
    public boolean isSuitable(Update update) {
        String text = Optional.ofNullable(update.message())
                .map(Message::text)
                .orElseGet(() -> Optional.ofNullable(update.callbackQuery())
                        .map(CallbackQuery::data)
                        .orElse(null));
        return text != null && (text.equals(INFO) || text.equals(TAKE) ||
                text.equals(REPORT) || text.equals(VOLUNTEER));
    }

}
