package pro.sky.teamproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    /**
     * Needed, for TelegramBot <br>
     * To <b>execute</b> commands
     */
    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /**
     * process updates and main logic
     * @param updates needed to process updates
     * @return confirmed updates
     */
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Process your updates here
            var messageText = update.message().text();
            var chatId = update.message().chat().id();

            telegramBotStart(updates,chatId, messageText);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    /**
     * Starts the bot and <br>
     * choose a pet dog or cat
     * @param updates needed to process updates
     * @param chatId id of the chat
     * @param messageText text of the message
     */
    private void telegramBotStart(List<Update> updates, Long chatId, String messageText) {
        updates.forEach(update -> {
            logger.info("Bot started: {}", update);

            if (messageText.equals("/start")) { // Думаю можно вынести в отдельный метод callbackData
                InlineKeyboardMarkup markup = new InlineKeyboardMarkup(
                        new InlineKeyboardButton("Приют для кошек").callbackData("Cat"),
                        new InlineKeyboardButton("Приют для собак").callbackData("Dog"));

                telegramBot.execute(new SendMessage(chatId, "Привет, " + update.message().from().firstName() + "!\n" +
                        "Я - бот, который поможет выбрать питомца для вас!\n" +
                        "Выберите приют:" +
                        "\nПриют для кошек\n" +
                        "Приют для собак\n" +
                        "Выбрать оба приюта нельзя:")
                        .replyMarkup(markup));

//java.lang.NullPointerException: Cannot invoke "com.pengrad.telegrambot.model.Message.text()" because the return value
// of "com.pengrad.telegrambot.model.Update.message()" is null хз почему так происходит не могу найти проблему

//                if (update.callbackQuery() != null) {
//                    String callbackData = update.callbackQuery().data();
//
//                    if (callbackData.equals("Cat")) {
//                        // Отправить информацию о кошке
//                        telegramBot.execute(new SendMessage(chatId, "Информация о кошке"));
//                    } else if (callbackData.equals("Dog")) {
//                        // Отправить информацию о собаке
//                        telegramBot.execute( new SendMessage(chatId, "Информация о собаке"));
//                    }
//                }
        }else {
                telegramBot.execute(new SendMessage(chatId, "Выбрать оба приюта нельзя:"));
            }
        });
    }

}
