package digital.typeb.hello_world.controller;


import digital.typeb.hello_world.service.HelloService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    @DisplayName("200: valid name (capitalized in response)")
    void shouldReturnHelloMessage_whenValidName() throws Exception {
        Mockito.when(helloService.getGreeting("Alice")).thenReturn("Hello Alice");

        mockMvc.perform(get("/hello-world").param("name", "Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }

    @Test
    @DisplayName("200: valid lowercase name")
    void shouldReturnHelloMessage_whenValidLowercaseName() throws Exception {
        Mockito.when(helloService.getGreeting("mike")).thenReturn("Hello Mike");

        mockMvc.perform(get("/hello-world").param("name", "mike"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Mike"));
    }

    @Test
    @DisplayName("400: name starts after M (e.g., N..Z)")
    void shouldReturnError_whenNameStartsAfterM() throws Exception {
        Mockito.when(helloService.getGreeting("Nina")).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "Nina"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("400: missing name param")
    void shouldReturnError_whenMissingName() throws Exception {
        Mockito.when(helloService.getGreeting(null)).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("400: blank name")
    void shouldReturnError_whenBlankName() throws Exception {
        Mockito.when(helloService.getGreeting("   ")).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "   "))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("400: non-alphabetic first char")
    void shouldReturnError_whenNonAlphabeticFirstChar() throws Exception {
        Mockito.when(helloService.getGreeting("1abc")).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "1abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("400: non-alphabetic in middle/end")
    void shouldReturnError_whenNonAlphabeticInside() throws Exception {
        Mockito.when(helloService.getGreeting("A1ice")).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "A1ice"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("Boundary: M is allowed (200) / N is rejected (400)")
    void boundaryMAndN() throws Exception {
        Mockito.when(helloService.getGreeting("Mark")).thenReturn("Hello Mark");
        Mockito.when(helloService.getGreeting("Nora")).thenThrow(new IllegalArgumentException("Invalid Input"));

        mockMvc.perform(get("/hello-world").param("name", "Mark"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Mark"));

        mockMvc.perform(get("/hello-world").param("name", "Nora"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Input"));
    }

    @Test
    @DisplayName("200: trims surrounding spaces")
    void trimsSpacesAndReturnsOk() throws Exception {
        Mockito.when(helloService.getGreeting("  alice  ")).thenReturn("Hello Alice");

        mockMvc.perform(get("/hello-world").param("name", "  alice  "))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello Alice"));
    }
}