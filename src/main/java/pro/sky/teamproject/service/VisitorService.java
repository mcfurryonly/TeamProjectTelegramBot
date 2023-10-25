package pro.sky.teamproject.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import pro.sky.teamproject.commands.StartHandler;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.repository.VisitorRepository;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final StartHandler startHandler;

    public VisitorService(VisitorRepository visitorRepository, StartHandler startHandler) {
        this.visitorRepository = visitorRepository;
        this.startHandler = startHandler;
    }

    public void handleTelegramUser(Long telegramUserId, Update update) {
        Visitor visitor = visitorRepository.findByTelegramUserId(telegramUserId);

        if (visitor == null) {
            visitorRepository.save(new Visitor(telegramUserId));
            startHandler.forNewUser(update);
        }else{
            startHandler.handle(update);
        }
    }

}
