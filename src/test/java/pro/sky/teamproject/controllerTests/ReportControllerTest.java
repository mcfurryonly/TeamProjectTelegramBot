package pro.sky.teamproject.controllerTests;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {
    @InjectMocks
    private ReportController reportController;
    @Mock
    private ReportService reportService;


    @Test
    public void getReportById() {
        byte[] pictures = {1, 2, 3};
        Long actualId = 123L;
        Report expectedReport = new Report(new Visitor(), pictures, "123");
        expectedReport.setId(actualId);
        when(reportService.getReport(actualId)).thenReturn(expectedReport);
        assertThat(reportController.getReport(actualId))
                .isEqualTo(ResponseEntity.ok(expectedReport));
    }

}
