package digital.typeb.hello_world.service.impl;

import digital.typeb.hello_world.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloServiceImpl  implements HelloService {
    @Override
    public String getGreeting(String name) {
        log.info("Processing greeting request for name: {}", name);

        // Validate input
        if (name == null || name.trim().isEmpty()) {
            log.warn("Rejected request: name is null or empty");
            throw new IllegalArgumentException("Invalid Input");
        }

        String trimmed = name.trim();
        char first = trimmed.charAt(0);

        // Allow only alphabetic names (A–Z or a–z, no digits or special chars)
        if (!trimmed.matches("^[A-Za-z]+$")) {
            log.warn("Rejected request: name contains non-alphabetic characters -> {}", name);
            throw new IllegalArgumentException("Invalid Input");
        }

        // Only accept names starting with letters A–M
        char upper = Character.toUpperCase(first);
        boolean inFirstHalf = (upper >= 'A' && upper <= 'M');
        if (!inFirstHalf) {
            log.warn("Rejected request: name starts with letter after M -> {}", name);
            throw new IllegalArgumentException("Invalid Input");
        }

        // Build the greeting
        String pretty = trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1);
        String message = "Hello " + pretty;

        log.info("Successfully generated greeting for '{}': {}", name, message);
        return message;
    }
}
