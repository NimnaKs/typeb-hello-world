package digital.typeb.hello_world.controller;

import digital.typeb.hello_world.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hello-world")
public class HelloController {

    private final HelloService helloService;

    @Operation(
            summary = "Greet a user",
            description = "Returns a greeting if name starts with A–M (case-insensitive). Otherwise returns 400 with an error message.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Greeting returned",
                            content = @Content(schema = @Schema(example = "{\"message\":\"Hello Alice\"}"))),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content(schema = @Schema(example = "{\"error\":\"Invalid Input\"}")))
            }
    )
    @GetMapping
    public ResponseEntity<Map<String, String>> hello(
            @Parameter(description = "User name (letters only, must start with A–M)", example = "Alice")
            @RequestParam(value = "name", required = false) String name) {
        String message = helloService.getGreeting(name);
        return ResponseEntity.ok(Map.of("message", message));
    }

}
