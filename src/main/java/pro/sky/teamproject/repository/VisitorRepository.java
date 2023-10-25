package pro.sky.teamproject.repository;

import com.pengrad.telegrambot.model.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.teamproject.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Visitor findByTelegramUserId(Long telegramUserId);

}
