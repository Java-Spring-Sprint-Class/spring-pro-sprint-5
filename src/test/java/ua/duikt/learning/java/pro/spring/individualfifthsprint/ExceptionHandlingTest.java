package ua.duikt.learning.java.pro.spring.individualfifthsprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class ExceptionHandlingTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("1. 200 OK: A normal request should succeed")
    void shouldReturnOk() throws Exception {
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product 1"));
    }

    @Test
    @DisplayName("2. 404 Not Found: ProductNotFoundException is handled correctly")
    void shouldHandleNotFound() throws Exception {
        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.statusCode").value(404));
    }

    @Test
    @DisplayName("3. 400 Bad Request: IllegalArgumentException is handled correctly")
    void shouldHandleBadRequest() throws Exception {
        mockMvc.perform(get("/products/update").param("price", "-50"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Price cannot be negative"));
    }

    @Test
    @DisplayName("4. 500 Internal Server Error: Generic Exception is handled correctly")
    void shouldHandleGenericError() throws Exception {
        mockMvc.perform(get("/products/dangerous"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.timestamp").exists());
    }
}

