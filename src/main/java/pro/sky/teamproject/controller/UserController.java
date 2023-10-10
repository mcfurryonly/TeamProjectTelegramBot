package pro.sky.teamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
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
    @PostMapping("/add-new-data")
    public ResponseEntity<String> addNewData(@RequestParam Long id,
                                             @RequestParam String number) {
        return ResponseEntity.ok("test");
    }
}
