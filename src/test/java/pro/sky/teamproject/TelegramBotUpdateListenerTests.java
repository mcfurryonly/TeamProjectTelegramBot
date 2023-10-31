package pro.sky.teamproject;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.teamproject.commands.StartHandler;
import pro.sky.teamproject.listener.TelegramBotUpdatesListener;
import pro.sky.teamproject.repository.VisitorRepository;
import pro.sky.teamproject.service.VisitorService;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelegramBotUpdateListenerTests {
    @Mock
    private TelegramBot telegramBot;
    @Mock
    private StartHandler startHandler;
    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    /**
     * Тест для проверки метода process UpdatesListener-а
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void checkStartCommand() throws IOException, URISyntaxException {
        Long userId = 1234567809L;
        //Paths.of вызывает от главной директории - там не желательно хранить файлы, поэтому создали
        //ресурсы и создали там json и файл start.json и тд
        // BotUtils класс пенграда который обрабатывает Update и записывает в toJson или считывает с fromJson
        //Из библиотеки nio мы берем класс Files чтобы считать данные из файла
        //readString принимает путь Path до файла, а чтобы его достать, мы используем Paths.get
        //toURI преобразование URL к URI, который необходим в Paths.get
        Update update = BotUtils.fromJson(Files.readString(
                Paths.get(TelegramBotUpdateListenerTests.class
                        .getResource("json/start.json").toURI())), Update.class);
//        Mockito.doNothing().when(visitorService).addNewUser(userId);
//        when(visitorRepository.findByTelegramUserId(userId)).thenReturn(null);
        Assertions.assertThat(telegramBotUpdatesListener.process(new ArrayList<>(){{
            add(update);
        }})).isEqualTo(UpdatesListener.CONFIRMED_UPDATES_ALL);


    }


}
