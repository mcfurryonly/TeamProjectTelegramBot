package pro.sky.teamproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShelterController {
    /**
     * Этот метод возвращает информацию о выбранном приюте
     *
     * @param id это идентификатор приюта
     * @return возвращаем ResponseEntity с тестовыми данными
     */
    @Operation(summary = "Get info by id", description = "Get info of shelter by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "When got info about shelter"),
            @ApiResponse(responseCode = "404", description = "Info not found"),
            @ApiResponse(responseCode = "500", description = "Server couldn't handle the request")
    })
    @GetMapping("/info")
    public ResponseEntity<String> getInfo(@PathVariable Long id) {
        return ResponseEntity.ok("test");
    }

    /**
     * Этот метод возвращает информацию о схеме проезда
     *
     * @param id это идентификатор приюта
     * @return возвращаем ResponseEntity с тестовыми данными
     */

    @Operation(summary = "get schema", description = "get schema of location with image",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "We got location of shelter",
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE
                            )),
                    @ApiResponse(responseCode = "400",
                            description = "couldn't find id shelter"),
                    @ApiResponse(responseCode = "404",
                            description = "couldn't find schema for this shelter"),
                    @ApiResponse(responseCode = "500",
                            description = "Server Error"
                    )
            })
    @GetMapping("/schema")
    public ResponseEntity<String> getSchema(@PathVariable Long id) {
        return ResponseEntity.ok("test");
    }

    /**
     * Этот метод возвращает информацию о схеме проезда
     *
     * @param id это идентификатор приюта
     * @return возвращаем ResponseEntity с тестовыми данными
     */

    @Operation(summary = "get address", description = "get full address with contact details",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "address is provided",
                            content = @Content(
                                    mediaType = MediaType.TEXT_PLAIN_VALUE
                            )),
                    @ApiResponse(responseCode = "404",
                            description = "couldn't find address"),
                    @ApiResponse(responseCode = "500",
                            description = "Server Error"
                    )
            })
    @GetMapping("/address")
    public ResponseEntity<String> getAddress(@PathVariable Long id, @PathVariable String address) {
        return ResponseEntity.ok("test");
    }


}
