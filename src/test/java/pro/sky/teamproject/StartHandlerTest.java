package pro.sky.teamproject;

import com.google.gson.reflect.TypeToken;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.AbstractSendRequest;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import liquibase.pro.packaged.S;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.teamproject.commands.Constants;
import pro.sky.teamproject.commands.StartHandler;
import pro.sky.teamproject.service.VisitorService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class StartHandlerTest {
    @InjectMocks
    private StartHandler startHandler;
    @Mock
    private VisitorService visitorService;
    @Mock
    private TelegramBot bot;

    private final Long CHAT_ID = 1234567809L;

    @Test
    public void checkMessageTest() throws URISyntaxException, IOException {
        Update update = BotUtils.fromJson(Files.readString(
                Paths.get(TelegramBotUpdateListenerTests.class
                        .getResource("json/start.json").toURI())), Update.class);
        startHandler.handle(update);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        //ArgumentCaptor класс для поимки ответа от бота для класса SendMessage, который содержит ответ для пользователя
        Mockito.verify(bot).execute(argumentCaptor.capture());
        //verify проверка того, что при вызове execute бота есть ответ, который кладется в argumentCaptor соответсвующий sendMessage
        SendMessage actual = argumentCaptor.getValue();
        assertThat(actual.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actual.getParameters().get("text"))
                .isEqualTo("Добро пожаловать в приложение");
        assertThat(actual.getParameters().get("reply_markup")).isNotNull();
        assertThat(actual.getParameters().get("reply_markup").getClass())
                .isEqualTo(InlineKeyboardMarkup.class);
        assertThat(((InlineKeyboardMarkup) actual.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].text()).isEqualTo("Приют собак");
        assertThat(((InlineKeyboardMarkup) actual.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][1].text()).isEqualTo("Приют кошек");
        assertThat(((InlineKeyboardMarkup) actual.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][1].callbackData()).isEqualTo("/cat");
    }

    @Test
    public void checkCallbackShelterDog() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_shelter.json").toURI()));
        Update update = BotUtils.fromJson(json, Update.class);
        startHandler.handle(update);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        assertThat(actual.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actual.getParameters().get("text")).isEqualTo("Выберите действие ");
        assertThat(((InlineKeyboardMarkup) actual.getParameters().get("reply_markup"))
                .inlineKeyboard()[0].length).isEqualTo(5);
        assertThat(((InlineKeyboardMarkup) actual.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("/infoDog");
    }

    @Test
    public void checkCallbackShelterCat() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_shelter.json").toURI()));
        json = json.replace("/dog", "/cat");
        Update updateCat = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateCat);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actualCat = argumentCaptor.getValue();
        assertThat(actualCat.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualCat.getParameters().get("text")).isEqualTo("Выберите действие ");
        assertThat(((InlineKeyboardMarkup) actualCat.getParameters().get("reply_markup"))
                .inlineKeyboard()[0].length).isEqualTo(5);
        assertThat(((InlineKeyboardMarkup) actualCat.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("/infoCat");

    }

    @Test
    public void checkReturnToMainMenu() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_back.json").toURI()));
        Update updateBack = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateBack);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actualBack = argumentCaptor.getValue();
        assertThat(actualBack.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualBack.getParameters().get("text")).isEqualTo("Добро пожаловать в приложение");
        assertThat(((InlineKeyboardMarkup) actualBack.getParameters().get("reply_markup"))
                .inlineKeyboard()[0].length).isEqualTo(2);
        //количество столбиков в строке
    }

    @Test
    public void checkInfoDog() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_info.json").toURI()));
        Update updateInfo = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateInfo);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actualInfo = argumentCaptor.getValue();
        assertThat(actualInfo.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualInfo.getParameters().get("text")).isEqualTo("Выберите действие");
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard().length).isEqualTo(7);
        //количество строк
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].text()).isEqualTo("О приюте");
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("aboutDog");
    }

    @Test
    public void checkInfoCat() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_info.json").toURI()));
        json = json.replace("/infoDog", "/infoCat");
        Update updateInfo = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateInfo);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actualInfo = argumentCaptor.getValue();
        assertThat(actualInfo.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualInfo.getParameters().get("text")).isEqualTo("Выберите действие");
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard().length).isEqualTo(7);
        //количество строк
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].text()).isEqualTo("О приюте");
        assertThat(((InlineKeyboardMarkup) actualInfo.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("aboutCat");
    }

    @Test
    public void checkTakeAnimal() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_take.json").toURI()));
        Update updateTake = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateTake);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actualTake = argumentCaptor.getValue();
        assertThat(actualTake.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualTake.getParameters().get("text")).isEqualTo("Выберите действие");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard().length).isEqualTo(12);
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].text()).isEqualTo("Правила знакомства с животным");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("meetRulesDog");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[11][0].callbackData()).isEqualTo("/backToMain");
    }

    @Test
    public void checkTakeAnimalCat() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_take.json").toURI()));
        json = json.replace("/takeDog", "/takeCat");
        Update updateTake = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateTake);
        ArgumentCaptor<SendMessage> argumentCaptorCat = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptorCat.capture());
        SendMessage actualTake = argumentCaptorCat.getValue();
        assertThat(actualTake.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualTake.getParameters().get("text")).isEqualTo("Выберите действие");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard().length).isEqualTo(10);
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].text()).isEqualTo("Правила знакомства с животным");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("meetRulesCat");
        assertThat(((InlineKeyboardMarkup) actualTake.getParameters().get("reply_markup"))
                .inlineKeyboard()[9][0].callbackData()).isEqualTo("/backToMain");
    }

    @Test
    public void checkReport() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_report.json").toURI()));
        Update updateReport = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateReport);
        ArgumentCaptor<AbstractSendRequest> argumentCaptor = ArgumentCaptor.forClass(AbstractSendRequest.class);
        Mockito.verify(bot, Mockito.times(2)).execute(argumentCaptor.capture());
        SendMessage actualReport = (SendMessage) argumentCaptor.getAllValues().get(0);
        assertThat(actualReport.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualReport.getParameters().get("text")).isEqualTo("Писать отчет нужно в описании к фотографии одним сообщением: ");
        SendPhoto actualReport2 = (SendPhoto) argumentCaptor.getAllValues().get(1);
        assertThat(actualReport2.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualReport2.getParameters().get("text")).isNull();
        assertThat(actualReport2.getParameters().get("caption")).isEqualTo("Описание состояния животного");
        assertThat(actualReport2.getParameters().get("photo")).isNotNull();
    }

    @Test
    public void checkVolunteer() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_volunteer.json").toURI()));
        Update updateVolunteer = BotUtils.fromJson(json, Update.class);
        startHandler.handle(updateVolunteer);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        assertThat(actual.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actual.getParameters().get("text")).isEqualTo("Вы можете связаться с волонтером по этому номеру +382 688 ***");
    }

    @Test
    public void checkAllCommandsForDog() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/callback_commands.json").toURI()));
        List<Update> updateCommands = List.of(BotUtils.fromJson(json, Update[].class));
        updateCommands.forEach(el->startHandler.handle(el));
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot, Mockito.times(updateCommands.size())).execute(argumentCaptor.capture());
        List<SendMessage> actualMessages = argumentCaptor.getAllValues();
        for (int i = 0; i < actualMessages.size(); i++) {
            String expected = Constants.allCommandsForDog.get(updateCommands.get(i).callbackQuery().data());
            assertThat(actualMessages.get(i).getParameters().get("text")).isEqualTo(expected);
        }


    }

}
