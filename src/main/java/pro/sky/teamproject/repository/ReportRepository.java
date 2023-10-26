package pro.sky.teamproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.teamproject.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

//    @Query(value = "SELECT picture FROM report WHERE id=?", nativeQuery = true)
//    byte[] getPicture(Long id);

}
