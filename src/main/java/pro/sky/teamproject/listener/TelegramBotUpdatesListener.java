package pro.sky.teamproject.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.teamproject.commands.DescriptionHandler;
import pro.sky.teamproject.commands.StartHandler;

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

    private final StartHandler startHandler;

    private DescriptionHandler descriptionHandler;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, StartHandler startHandler) {
        this.telegramBot = telegramBot;
        this.startHandler = startHandler;
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

            startHandler.handle(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
