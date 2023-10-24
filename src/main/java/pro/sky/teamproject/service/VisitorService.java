package pro.sky.teamproject.service;

import org.springframework.stereotype.Service;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.repository.VisitorRepository;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public void addNewUser(Long telegramUserId) {
        if (visitorRepository.findByTelegramUserId(telegramUserId) == null) {
            visitorRepository.save(new Visitor(telegramUserId));
        }
    }
}
