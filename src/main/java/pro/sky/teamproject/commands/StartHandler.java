package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Component;
import pro.sky.teamproject.service.VisitorService;

import javax.persistence.EntityManager;
import java.io.File;


@Component
public class StartHandler implements Handler {
    private final VisitorService visitorService;
    private final TelegramBot bot;
    private final EntityManager entityManager;
    private final String INFO_DOG = "/infoDog";
    private final String TAKE_DOG = "/takeDog";
    private final String REPORT_DOG = "/reportDog";
    private final String INFO_CAT = "/infoCat";
    private final String TAKE_CAT = "/takeCat";
    private final String REPORT_CAT = "/reportCat";
    private final String VOLUNTEER = "/volunteer";
    private final String BACK_TO_MAIN = "/backToMain";
    private final String DOG = "/dog";
    private final String CAT = "/cat";

    public StartHandler(VisitorService visitorService, TelegramBot bot, EntityManager entityManager) {
        this.visitorService = visitorService;
        this.bot = bot;
        this.entityManager = entityManager;
    }

    /**
     * @param update creates a buttons and sends a message
     */
    public void handle(Update update) {
        if (update.message() != null) {
            long chatId = update.message().chat().id();
            showMainMenu(chatId);
        } else if (update.callbackQuery() != null) {
            var chatId = update.callbackQuery().message().chat().id();
            var callbackData = update.callbackQuery().data();

            InlineKeyboardButton mainButton = new InlineKeyboardButton("Возврат в главное меню")
                    .callbackData(BACK_TO_MAIN);
            InlineKeyboardButton volunteerButton = new InlineKeyboardButton("Позвать волонтера")
                    .callbackData(VOLUNTEER);

            if (callbackData.equals(DOG)) {
                InlineKeyboardButton infoButtonDog = new InlineKeyboardButton("Инфо")
                        .callbackData(INFO_DOG);
                InlineKeyboardButton takeButton = new InlineKeyboardButton("Взять")
                        .callbackData(TAKE_DOG);
                InlineKeyboardButton reportButton = new InlineKeyboardButton("Отчет")
                        .callbackData(REPORT_DOG);
                InlineKeyboardMarkup markupDog = new InlineKeyboardMarkup(infoButtonDog, takeButton,
                        reportButton, volunteerButton, mainButton);
                bot.execute(new SendMessage(chatId, "Выберите действие ").replyMarkup(markupDog));
            } else if (callbackData.equals(CAT)) {
                InlineKeyboardButton infoButtonCat = new InlineKeyboardButton("Инфо")
                        .callbackData(INFO_CAT);
                InlineKeyboardButton takeButton1 = new InlineKeyboardButton("Взять")
                        .callbackData(TAKE_CAT);
                InlineKeyboardButton reportButton1 = new InlineKeyboardButton("Отчет")
                        .callbackData(REPORT_CAT);
                InlineKeyboardMarkup markupCat = new InlineKeyboardMarkup(infoButtonCat, takeButton1,
                        reportButton1, volunteerButton, mainButton);
                bot.execute(new SendMessage(chatId, "Выберите действие ").replyMarkup(markupCat));
            } else if (callbackData.equals(BACK_TO_MAIN)) {
                showMainMenu(chatId);
            } else if (callbackData.equals(INFO_DOG)) {
                showInfo(chatId, "Dog");
            } else if (callbackData.equals(INFO_CAT)) {
                showInfo(chatId, "Cat");
            } else if (callbackData.equals(TAKE_DOG)) {
                showMenuTake(chatId, "Dog");
            } else if (callbackData.equals(TAKE_CAT)) {
                showMenuTake(chatId, "Cat");
            } else if (callbackData.equals(REPORT_DOG)) {
                bot.execute(new SendMessage(chatId, "Писать отчет нужно в описании к фотографии одним сообщением: "));
                bot.execute(new SendPhoto(chatId, new File("images.jpeg"))
                        .caption("Описание состояния животного").parseMode(ParseMode.HTML));
            } else if (callbackData.equals(REPORT_CAT)) {
                bot.execute(new SendMessage(chatId, "Писать отчет нужно в описании к фотографии одним сообщением. " +
                        "Пример отчета: "));
            } else if (callbackData.equals(VOLUNTEER)) {
                bot.execute(new SendMessage(chatId, "Вы можете связаться с волонтером по этому номеру +382 688 ***"));
            } else if (Constants.allCommandsForDog.containsKey(callbackData)) {
                bot.execute(new SendMessage(chatId, Constants.allCommandsForDog.get(callbackData)));
            } else if (Constants.allCommandsForCat.containsKey(callbackData)) {
                bot.execute(new SendMessage(chatId, Constants.allCommandsForCat.get(callbackData)));
            }
        }
    }
    public void showMenuTake(Long chatId, String animalType) {
        int countRows = animalType.equals("Dog") ? 12 : 10;
        InlineKeyboardButton[][] inlineKeyboardButtons = new InlineKeyboardButton[countRows][1];
        inlineKeyboardButtons[0][0] = new InlineKeyboardButton("Правила знакомства с животным")
                .callbackData("meetRules" + animalType);
        inlineKeyboardButtons[1][0] = new InlineKeyboardButton("Список документов, для взятия животного")
                .callbackData("documents" + animalType);
        inlineKeyboardButtons[2][0] = new InlineKeyboardButton("Список рекомендаций по транспортировке животного")
                .callbackData("transportationRecommendation" + animalType);
        inlineKeyboardButtons[3][0] = new InlineKeyboardButton("Список рекомендаций по обустройству дома для малыша")
                .callbackData("smallHouseRecommendation" + animalType);
        inlineKeyboardButtons[4][0] = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослого животного")
                .callbackData("adultHouseRecommendation" + animalType);
        inlineKeyboardButtons[5][0] = new InlineKeyboardButton("Список рекомендаций по обустройству дома для животного с ограниченными возможностями")
                .callbackData("disabledHouseRecommendation" + animalType);
        int index = 6;
        if (animalType.equals("Dog")) {
            inlineKeyboardButtons[index++][0] = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой")
                    .callbackData("cynologistAdvice" + animalType);
            inlineKeyboardButtons[index++][0] = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним")
                    .callbackData("recommendationsOfCynologists" + animalType);
        }
        inlineKeyboardButtons[index++][0] = new InlineKeyboardButton("Список причин отказа в приеме животного")
                .callbackData("refuseReasons" + animalType);
        inlineKeyboardButtons[index++][0] = new InlineKeyboardButton("Контактные данные клиента для связи")
                .callbackData("userContactDetails" + animalType);
        inlineKeyboardButtons[index++][0] = new InlineKeyboardButton("Позвать волонтера")
                .callbackData(VOLUNTEER);
        inlineKeyboardButtons[index][0] = new InlineKeyboardButton("Назад в главное меню")
                .callbackData(BACK_TO_MAIN);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(inlineKeyboardButtons);
        bot.execute(new SendMessage(chatId, "Выберите действие").replyMarkup(inlineKeyboardMarkup));
    }

    public void showInfo(Long chatId, String animalType) { //Идея такова что при первом нажатии это меню всплывет, а при втором нажатии на старт будет другое меню уже
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup(new InlineKeyboardButton[][]{
                {new InlineKeyboardButton("О приюте").callbackData("about" + animalType)},
                {new InlineKeyboardButton("Расписание и адрес").callbackData("schedule" + animalType)},
                {new InlineKeyboardButton("Контакты охраны").callbackData("security" + animalType)},
                {new InlineKeyboardButton("Рекомендации по безопасности").callbackData("safety" + animalType)},
                {new InlineKeyboardButton("Оставить контактные данные").callbackData("contact" + animalType)},
                {new InlineKeyboardButton("Позвать волонтера").callbackData(VOLUNTEER)},
                {new InlineKeyboardButton("Назад в главное меню").callbackData(BACK_TO_MAIN)}
        });
        SendMessage sendMessage = new SendMessage(chatId, "Выберите действие")
                .replyMarkup(keyboard);
        bot.execute(sendMessage);
    }
    public void showMainMenu(Long chatId) {
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют собак")
                .callbackData(DOG);
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют кошек")
                .callbackData(CAT);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(dogButton, catButton);
        visitorService.addNewUser(chatId);
        SendMessage sendMessage = new SendMessage(chatId, "Добро пожаловать в приложение")
                .replyMarkup(markup);
        bot.execute(sendMessage);
    }
}