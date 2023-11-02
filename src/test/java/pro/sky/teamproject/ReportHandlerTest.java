package pro.sky.teamproject;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.teamproject.commands.ReportHandler;
import pro.sky.teamproject.service.ReportService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportHandlerTest {
    @InjectMocks
    private ReportHandler reportHandler;
    @Spy //для того, чтоб запрос был в telegram api, чтобы получить реальный файл, который там хранится
    private TelegramBot bot = new TelegramBot("6470913414:AAFucAeZwn6k1TiyXQdHLDhoW55BgoUPJ0c");
    @Mock
    private ReportService reportService;


    @Test
    public void checkEmptyCaption() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/report_empty_caption.json").toURI()));
        Update updateCommand = BotUtils.fromJson(json, Update.class);
        reportHandler.handle(updateCommand);
        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        Mockito.verify(bot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();
        assertThat(actual.getParameters().get("text"))
                .isEqualTo("Необходимо отправить фото с описанием состояния животного");
    }

    @Test
    public void saveReport() throws URISyntaxException, IOException {
        String json = Files.readString(Paths.get(TelegramBotUpdateListenerTests.class
                .getResource("json/report_caption_with_photo.json").toURI()));
        Update updateCommand = BotUtils.fromJson(json, Update.class);
        reportHandler.handle(updateCommand);
        ArgumentCaptor<BaseRequest> argumentCaptor = ArgumentCaptor.forClass(BaseRequest.class);
        Mockito.verify(bot, times(2)).execute(argumentCaptor.capture());
        SendMessage actual = (SendMessage) argumentCaptor.getAllValues().get(1);
        assertThat(actual.getParameters().get("text"))
                .isEqualTo("Ваш отчет принят");

    }



}
