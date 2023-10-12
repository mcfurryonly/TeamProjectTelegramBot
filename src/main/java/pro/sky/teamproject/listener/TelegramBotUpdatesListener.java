package pro.sky.teamproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.teamproject.commands.Handler;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

//    @Autowired
    private final TelegramBot telegramBot;
    private final List<Handler> handlers;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, List<Handler> handlers) {
        this.telegramBot = telegramBot;
        this.handlers = handlers;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        //Проходим все updates это сообщение от клиента
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            //Обходим все команды и находим подходящие к этому сообщению
            handlers.stream()
                    .filter(handler -> handler.isSuitable(update))
                    .forEach(handler -> handler.handle(update));
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
