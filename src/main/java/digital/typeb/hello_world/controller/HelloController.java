package digital.typeb.hello_world.controller;

import digital.typeb.hello_world.service.HelloService;
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

    @GetMapping
    public ResponseEntity<Map<String, String>> hello(@RequestParam(value = "name", required = false) String name) {
        String message = helloService.getGreeting(name);
        return ResponseEntity.ok(Map.of("message", message));
    }

}
