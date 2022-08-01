package com.example.sportclopedia.web;

import com.example.sportclopedia.service.HallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HallControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    HallService hallService;

    @Test
    @WithMockUser(username = "user", roles = {"USER","ADMIN"})
    void testHallsPageShown() throws Exception {

        mockMvc.perform(get("/halls"))
                .andExpect(status().isOk())
                .andExpect(view().name("halls"));

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER","ADMIN"})
    void testAddHallsPageShown() throws Exception {

        mockMvc.perform(get("/halls/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("hall-add"));

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER","ADMIN"})
    void testAddHall() throws Exception {

        mockMvc.perform(post("/halls/add")
                        .param("name", "sila")
                        .param("address", "Plovdiv 4000")
                        .param("capacity", "400")
                        .param("availableOn", "2022-08-21T16:53")
                        .param("description","yes no yes!yes no yes!yes no yes!")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/halls"));

    }
    @Test
    @WithMockUser(username = "user", roles = {"USER","ADMIN"})
    void testDeleteHall() throws Exception {

        mockMvc.perform(post("/halls/add")
                        .param("id","1")
                        .param("name", "sila")
                        .param("address", "Plovdiv 4000")
                        .param("capacity", "400")
                        .param("availableOn", "2022-08-21T16:53")
                        .param("description","yes no yes!yes no yes!yes no yes!")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/halls"));

        mockMvc.perform(get("/halls/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/halls"));

    }


}
