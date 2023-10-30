package pro.sky.teamproject.commands;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import pro.sky.teamproject.service.ReportService;

import java.io.IOException;
import java.io.InputStream;

@Component
public class ReportHandler implements Handler {
    private final TelegramBot bot;
    private final ReportService reportService;
    public ReportHandler(TelegramBot bot, ReportService reportService) {
        this.bot = bot;
        this.reportService = reportService;
    }

    @Override
    public void handle(Update update) {
        if (update.message().caption() == null || update.message().caption().isEmpty()) {
            bot.execute(new SendMessage(update.message().chat().id(),
                    "Необходимо отправить фото с описанием состояния животного"));
            return;
        }
        PhotoSize[] photoSize = update.message().photo();//создали массив, где есть картинка, но разных размеров
        GetFileResponse response = bot.execute(new GetFile(photoSize[photoSize.length - 1].fileId()));
        //Класс в пенграде для получения файла по id и мы получили ответ с последней картинку которая сама большая по размерам
        String path = bot.getFullFilePath(response.file());
        System.out.println(path);
        //и мы получаем путь к картинке "file_path": "photos/file_0.jpg" по адресу https://api.telegram.org/bot<bot_token>/getFile?file_id=the_file_id
        Request request = new Request.Builder().head().url(path).build();
        //создание запроса с путем картинки для дальнейшего вызова
        try (Response pathResponse = new OkHttpClient().newCall(request).execute()) {
            //Класс для получения ответа от телеграма на картинку, чтоб получить картинку
            //OkHttpClient это класс для отправки запросов в веб
            //newCall вызывает запрос на определенную страницу
            if (pathResponse.isSuccessful() && pathResponse.body() != null) {
                byte[] bytes = pathResponse.body().bytes();
                reportService.saveReport(update.message().from().id(),
                        bytes, update.message().caption());
                bot.execute(new SendMessage(update.message().chat().id(), "Ваш отчет принят"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
