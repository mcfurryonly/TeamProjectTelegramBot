package pro.sky.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.teamproject.model.Report;
@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
