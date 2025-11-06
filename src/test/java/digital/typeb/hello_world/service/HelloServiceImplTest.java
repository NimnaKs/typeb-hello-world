package digital.typeb.hello_world.service;

import digital.typeb.hello_world.service.impl.HelloServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HelloServiceImplTest {

    private final HelloService service = new HelloServiceImpl();

    @Test
    @DisplayName("Valid: A–M uppercase")
    void validUppercase() {
        assertEquals("Hello Alice", service.getGreeting("Alice"));
        assertEquals("Hello Mike", service.getGreeting("Mike"));
    }

    @Test
    @DisplayName("Valid: a–m lowercase (capitalizes in response)")
    void validLowercase() {
        assertEquals("Hello Alice", service.getGreeting("alice"));
        assertEquals("Hello Mark", service.getGreeting("mark"));
    }

    @Test
    @DisplayName("Valid: single-letter names within A–M")
    void validSingleLetter() {
        assertEquals("Hello A", service.getGreeting("A"));
        assertEquals("Hello M", service.getGreeting("M"));
        assertEquals("Hello A", service.getGreeting("a"));
        assertEquals("Hello M", service.getGreeting("m"));
    }

    @Test
    @DisplayName("Invalid: null or blank")
    void invalidNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting(null));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("   "));
    }

    @Test
    @DisplayName("Invalid: starts after M (N–Z)")
    void invalidStartsAfterM() {
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("Nancy"));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("zara"));
    }

    @Test
    @DisplayName("Invalid: non-letter first character")
    void invalidNonLetterFirstChar() {
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("1abc"));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("-alex"));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("_mike"));
    }

    @Test
    @DisplayName("Invalid: non-letter in middle or end")
    void invalidNonLetterInside() {
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("A1ice"));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("Al!ce"));
        assertThrows(IllegalArgumentException.class, () -> service.getGreeting("Al_ce"));
    }

    @Test
    @DisplayName("Valid: leading/trailing whitespace is trimmed")
    void trimsWhitespace() {
        assertEquals("Hello Alice", service.getGreeting("  Alice  "));
        assertEquals("Hello Mike", service.getGreeting("\tMike\n"));
    }
}