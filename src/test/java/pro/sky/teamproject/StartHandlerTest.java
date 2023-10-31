package pro.sky.teamproject;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import liquibase.pro.packaged.S;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.teamproject.commands.StartHandler;
import pro.sky.teamproject.service.VisitorService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        ArgumentCaptor<SendMessage> argumentCaptorCat = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptorCat.capture());
        SendMessage actualCat = argumentCaptorCat.getValue();
        assertThat(actualCat.getParameters().get("chat_id")).isEqualTo(CHAT_ID);
        assertThat(actualCat.getParameters().get("text")).isEqualTo("Выберите действие ");
        assertThat(((InlineKeyboardMarkup) actualCat.getParameters().get("reply_markup"))
                .inlineKeyboard()[0].length).isEqualTo(5);
        assertThat(((InlineKeyboardMarkup) actualCat.getParameters().get("reply_markup"))
                .inlineKeyboard()[0][0].callbackData()).isEqualTo("/infoCat");






    }

}
