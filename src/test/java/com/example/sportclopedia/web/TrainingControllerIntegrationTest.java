package com.example.sportclopedia.web;

import com.example.sportclopedia.model.enums.IntensityLevelEnum;
import com.example.sportclopedia.service.CoachService;
import com.example.sportclopedia.service.HallService;
import com.example.sportclopedia.service.SportService;
import com.example.sportclopedia.service.TrainingService;
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
public class TrainingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TrainingService trainingService;
    @MockBean
    SportService sportService;
    @MockBean
    HallService hallService;
    @MockBean
    CoachService coachService;

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testAddTrainingPageShown() throws Exception {

        mockMvc.perform(get("/trainings/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("training-add"));

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testTrainingsBySportPageShown() throws Exception {

        mockMvc.perform(get("/trainings/sport/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trainings-by-sports"));

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testAddTraining() throws Exception {

        mockMvc.perform(post("/trainings/add")
                        .param("name", "SILA")
                        .param("duration", "23")
                        .param("intensity", IntensityLevelEnum.Intermediate.name())
                        .param("startedOn", "2022-08-21T16:53")
                        .param("description", "this is some description!!!")
                        .param("coach", "Iliyan")
                        .param("sport", "Basketball")
                        .param("hall", "Pulse")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trainings/all"));

    }

    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testDeleteTraining() throws Exception {

        mockMvc.perform(post("/trainings/add")
                        .param("id", "1")
                        .param("name", "SILA")
                        .param("duration", "23")
                        .param("intensity", IntensityLevelEnum.Intermediate.name())
                        .param("startedOn", "2022-08-21T16:53")
                        .param("description", "this is some description!!!")
                        .param("coach", "Iliyan")
                        .param("sport", "Basketball")
                        .param("hall", "Pulse")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trainings/all"));

        mockMvc.perform(get("/trainings/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trainings/all"));

    }
    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testAllTrainingPageShown() throws Exception {

        mockMvc.perform(get("/trainings/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("trainings-all"));

    }
    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testMyTrainingsPageShown() throws Exception {

        mockMvc.perform(get("/trainings/user"))
                .andExpect(status().isOk())
                .andExpect(view().name("trainings-by-user"));

    }
    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    void testTrainingDetailsPageShown() throws Exception {

        mockMvc.perform(get("/trainings/details/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("training-details"));

    }

}
