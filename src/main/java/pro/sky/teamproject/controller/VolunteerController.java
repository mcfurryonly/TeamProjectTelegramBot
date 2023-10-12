package pro.sky.teamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VolunteerController {
    @Operation(summary = "approval", description = "approval for admission",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "TRUE/FALSE about approval and reason"
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Server Error"
                    )
            })
    @PostMapping("/volunteer")
    public ResponseEntity<Object> contactVolunteer(@RequestParam Long userId,
                                                   @RequestParam Long orderId) {
        return ResponseEntity.ok("test");
    }
}

