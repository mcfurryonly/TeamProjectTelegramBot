package pro.sky.teamproject.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import pro.sky.teamproject.controller.ReportController;
import pro.sky.teamproject.model.Report;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReportPhotoControllerTest {

    @Autowired
    private ReportController reportController;

    @Test
    public void getPhotoByIdReport() {
        Long idReport = 1L;
        ResponseEntity<Object> actual = reportController.getPhotoOfReport(idReport);
        assertThat(actual.getBody()).isNotNull();

    }

}
