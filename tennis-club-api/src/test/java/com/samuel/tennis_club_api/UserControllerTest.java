package com.samuel.tennis_club_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersShouldReturn200() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void getUserShouldReturn404() throws Exception {
        mockMvc.perform(get("/users/99999")).andExpect(status().isNotFound());
    }

    @Test
    public void postUserShouldReturn201() throws Exception {
        String email = "testuser-" + System.nanoTime() + "@example.com";
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("""
        {
            "name": "Test User",
            "email": "%s",
            "password": "password123"
        }
        """.formatted(email))).andExpect(status().isCreated());
    }

    @Test 
    public void putUserShouldReturn200() throws Exception {
        mockMvc.perform(put("/users/4").contentType(MediaType.APPLICATION_JSON).content("""
        {
            "name": "Updated User",
            "email": "updated@example.com",
            "password": "password123"
        }
        """)).andExpect(status().isOk());
    }

    @Test
    public void putUserShouldReturn404() throws Exception {
        mockMvc.perform(put("/users/99999").contentType(MediaType.APPLICATION_JSON).content("""
        {
            "name": "Updated User",
            "email": "updated@example.com",
            "password": "password123"
        }
        """)).andExpect(status().isNotFound());
    }

    @Test 
    public void deleteUserShouldReturn404() throws Exception {
        mockMvc.perform(delete("/users/999999")).andExpect(status().isNotFound());
    }
}