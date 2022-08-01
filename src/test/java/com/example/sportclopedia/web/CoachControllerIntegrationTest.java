package com.example.sportclopedia.web;

import com.example.sportclopedia.service.CoachService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
public class CoachControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    CoachService coachService;


    @Test
    @WithMockUser(username = "user", roles = {"ADMIN", "USER"})
    void testCoachesPageShown() throws Exception {

        mockMvc.perform(get("/coaches"))
                .andExpect(status().isOk())
                .andExpect(view().name("coaches"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN", "USER"})
    void testAddCoachPageShown() throws Exception {

        mockMvc.perform(get("/coaches/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("coach-add"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void testAddCoach() throws Exception {

        mockMvc.perform(post("/coaches/add")
                        .param("fullName", "Iliyan Stavrev")
                        .param("age", "38")
                        .param("coachSince", "2000-05-05")
                        .param("phoneNumber", "0899 99 99 99")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coaches"));

    }
    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void testDeleteCoach() throws Exception {

        mockMvc.perform(post("/coaches/add")
                        .param("id", "1")
                        .param("fullName", "Iliyan Stavrev")
                        .param("age", "38")
                        .param("coachSince", "2000-05-05")
                        .param("phoneNumber", "0899 99 99 99")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coaches"));

        mockMvc.perform(get("/coaches/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/coaches"));

    }
}
