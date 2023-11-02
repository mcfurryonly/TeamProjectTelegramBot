package pro.sky.teamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.teamproject.model.Visitor;
import pro.sky.teamproject.service.ReportService;
import pro.sky.teamproject.service.VisitorService;

@RestController
public class VisitorController {

    private final VisitorService visitorService;
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }
    @Operation(summary = "register new user", description = "registration of new user with phone number",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "user is registered",
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE
                            )),
                    @ApiResponse(responseCode = "500",
                            description = "Server Error"
                    )
            })
    @PostMapping("/addNewUser")
    public ResponseEntity<String> addNewUser(@RequestParam Long telegramUserId) {
       return ResponseEntity.ok(visitorService.addNewUser(telegramUserId));
    }

}
