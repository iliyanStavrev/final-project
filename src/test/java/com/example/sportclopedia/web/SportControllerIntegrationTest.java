package com.example.sportclopedia.web;

import com.example.sportclopedia.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class SportControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TrainingService trainingService;


    @Test
    void testSportPageShown() throws Exception {

        mockMvc.perform(get("/sports"))
                .andExpect(status().isOk())
                .andExpect(view().name("sports"));
    }

    @Test
    void testIndexPageShown() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}
