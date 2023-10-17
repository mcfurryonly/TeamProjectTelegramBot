package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;


@Component
public class StartHandler implements Handler {

    private final TelegramBot bot;

    public StartHandler(TelegramBot bot) {
        this.bot = bot;
    }

    /**
     * @param update creates a buttons and sends a message
     */
    public void handle(Update update) {
        String DOG = "/dog";
        String CAT = "/cat";
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
            var chatId = update.callbackQuery().message().chat().id();
            var callbackData = update.callbackQuery().data();

            String INFO = "/infoDog";
            String TAKE = "/takeDog";
            String REPORT = "/reportDog";
            String INFOCAT = "/infoCat";
            String TAKECAT = "/takeCat";
            String REPORTCAT = "/reportCat";
            String VOLUNTEER = "/volunteer";
            if (callbackData.equals(DOG)) {
                InlineKeyboardButton infoButton = new InlineKeyboardButton("Инфо").callbackData(INFO);

                InlineKeyboardButton takeButton = new InlineKeyboardButton("Взять").callbackData(TAKE);

                InlineKeyboardButton reportButton = new InlineKeyboardButton("Отчет").callbackData(REPORT);

                InlineKeyboardButton volunteerButton = new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER);

                var markup1 = new InlineKeyboardMarkup(infoButton, takeButton, reportButton, volunteerButton);
                bot.execute(new SendMessage(chatId, "Выберите действие ").replyMarkup(markup1));
                return;
            } else if (callbackData.equals(CAT)) {
                InlineKeyboardButton infoButton1 = new InlineKeyboardButton("Инфо").callbackData(INFOCAT);

                InlineKeyboardButton takeButton1 = new InlineKeyboardButton("Взять").callbackData(TAKECAT);

                InlineKeyboardButton reportButton1 = new InlineKeyboardButton("Отчет").callbackData(REPORTCAT);

                InlineKeyboardButton volunteerButton1 = new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER);

                var markup1 = new InlineKeyboardMarkup(infoButton1, takeButton1, reportButton1, volunteerButton1);
                bot.execute(new SendMessage(chatId, "Выберите действие ").replyMarkup(markup1));
                return;
            }

            if (callbackData.equals(INFO)) {
                bot.execute(new SendMessage(chatId, "Мы приют в котором очень любят собак мы готовы помочь вам с их выбором," +
                        "или просто проконсультировать какая порода вам больше подойдет "));

            } else if (callbackData.equals(TAKE)) {
                bot.execute(new SendMessage(chatId, "Что нужно чтоб взять собаку из приюта: "));

            }else if (callbackData.equals(REPORT)){
                bot.execute(new SendMessage(chatId, "Писать отчет нужно в описании к фотографии одним сообщением. " +
                        "Пример отчета: "));

            } else if (callbackData.equals(VOLUNTEER)) {
                bot.execute(new SendMessage(chatId, "Вы можете связаться с волонтером по этому номеру +382 688 ***"));

            } else if (callbackData.equals(INFOCAT)) {
                bot.execute(new SendMessage(chatId, "Мы приют в котором очень любят кошек мы готовы помочь вам с их выбором," +
                        "или просто проконсультировать какая порода вам больше подойдет "));

            } else if (callbackData.equals(TAKECAT)) {
                bot.execute(new SendMessage(chatId, "Что нужно чтоб взять кошку из приюта: "));

            } else if (callbackData.equals(REPORTCAT)) {
                bot.execute(new SendMessage(chatId, "Писать отчет нужно в описании к фотографии одним сообщением. " +
                        "Пример отчета: "));

            } else bot.execute(new SendMessage(chatId, "Извините я не могу вам помочь, но вы можете связаться" +
                    " с волонтером по этому номеру +382 688 ***"));
        }


    }



}
