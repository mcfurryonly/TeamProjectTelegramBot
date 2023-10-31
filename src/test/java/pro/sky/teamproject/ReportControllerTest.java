package pro.sky.teamproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import pro.sky.teamproject.controller.ReportController;
import pro.sky.teamproject.model.Report;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.service.ReportService;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;


    @Test
    public void getIdTest() {
        Long id = 1L;
        Report expectedReport = new Report(new Visitor(1234L, "name", "12345"),
                null, "description");
//        Report actualReport = reportController.getReport(id);
        when(reportService.getReport(id)).thenReturn(expectedReport);
        Assertions.assertEquals(ResponseEntity.ok(expectedReport), reportController.getReport(id));
    }

}
