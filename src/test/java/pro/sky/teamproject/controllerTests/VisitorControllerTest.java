package pro.sky.teamproject.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.teamproject.controller.VisitorController;
import pro.sky.teamproject.model.Report;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.service.VisitorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitorControllerTest {
    @InjectMocks
    private VisitorService visitorService;
    @Mock
    private VisitorController visitorController;

}
