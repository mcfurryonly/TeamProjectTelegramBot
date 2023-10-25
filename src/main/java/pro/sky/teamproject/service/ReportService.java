package pro.sky.teamproject.service;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.teamproject.model.Report;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.repository.ReportRepository;
import pro.sky.teamproject.repository.VisitorRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    private final VisitorRepository visitorRepository;
    public ReportService(ReportRepository reportRepository, VisitorRepository visitorRepository) {
        this.reportRepository = reportRepository;
        this.visitorRepository = visitorRepository;
    }

    public void saveReport(Long telegramUserId, byte[] photo, String description) {
        Visitor visitor = visitorRepository.findByTelegramUserId(telegramUserId);
        reportRepository.save(new Report(visitor, photo, description));
    }

    public Report getReport(Long id) {
        return reportRepository.findById(id).orElseThrow();
    }

//    public MultipartFile getPhotoOfReport(Long id) {
//
//    }
}
