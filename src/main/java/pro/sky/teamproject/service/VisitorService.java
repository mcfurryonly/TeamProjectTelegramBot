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

    public Visitor addNewUser(Long telegramUserId) {
        Visitor visitor = visitorRepository.findByTelegramUserId(telegramUserId);
        if (visitor == null) {
            return visitorRepository.save(new Visitor(telegramUserId));
        }
        return visitor;
    }
}
