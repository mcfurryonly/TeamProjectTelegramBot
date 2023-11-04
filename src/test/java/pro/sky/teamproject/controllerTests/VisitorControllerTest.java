package pro.sky.teamproject.controllerTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.sky.teamproject.controller.VisitorController;
import pro.sky.teamproject.model.Report;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.repository.VisitorRepository;
import pro.sky.teamproject.service.VisitorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VisitorControllerTest {
    @InjectMocks
    private VisitorController visitorController;
    @Mock
    private VisitorService visitorService;
    @Mock
    private VisitorRepository visitorRepository;

    @Test
    public void addNewVisitorTest() {
        Long tgIdVisitor = 123L;
        Visitor visitorExpected = new Visitor(tgIdVisitor);
        ResponseEntity<Visitor> responseExpected = ResponseEntity.ok(visitorExpected);
        when(visitorService.addNewUser(tgIdVisitor)).thenReturn(visitorExpected);
        assertThat(visitorController.addNewUser(tgIdVisitor)).isEqualTo(responseExpected);
    }

    @Test
    public void createVisitorWithErrorTest() {
        Long tgIdVisitor = -123L;
        ResponseEntity<Visitor> responseExpected = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        assertThat(visitorController.addNewUser(tgIdVisitor)).isEqualTo(responseExpected);
    }

    @Test
    public void addVisitorWhenExistTest() {
        Long tgIdVisitor = 2045609740L;
        Long id = 1L;
        Visitor visitorExpected = new Visitor(tgIdVisitor);
        visitorExpected.setId(id);
        ResponseEntity<Visitor> responseExpected = ResponseEntity.ok(visitorExpected);
        when(visitorService.addNewUser(tgIdVisitor)).thenReturn(visitorExpected);
        assertThat(visitorController.addNewUser(tgIdVisitor)).isEqualTo(responseExpected);
    }

}
