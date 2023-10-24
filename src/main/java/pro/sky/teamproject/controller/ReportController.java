package pro.sky.teamproject.controller;

import liquibase.pro.packaged.R;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.teamproject.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getReport(@PathVariable Long id) {
       return ResponseEntity.ok(reportService.getReport(id));
    }

//    @GetMapping("/{id}/photo")
//    public ResponseEntity<Object> getPhotoOfReport(@PathVariable Long id) {
//        return ResponseEntity.ok(reportService.getPhotoOfReport(id));
//    }

}
