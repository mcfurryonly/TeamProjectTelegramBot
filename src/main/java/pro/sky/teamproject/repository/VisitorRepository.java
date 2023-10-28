package pro.sky.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.teamproject.model.Visitor;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Visitor findByTelegramUserId(Long telegramUserId);
}
