package com.example.sportclopedia.web;

import com.example.sportclopedia.service.StatsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StatsService statsService;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN","USER"})
    void testStatsPageShown() throws Exception {

        mockMvc.perform(get("/statistic"))
                .andExpect(status().isOk())
                .andExpect(view().name("statistic"));
    }

}
