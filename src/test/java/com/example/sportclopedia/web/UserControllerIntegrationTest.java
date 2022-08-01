package com.example.sportclopedia.web;

import com.example.sportclopedia.model.dto.RegisterDto;
import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.model.enums.UserRoleEnum;
import com.example.sportclopedia.repository.UserRoleRepository;
import com.example.sportclopedia.service.AppUserDetailsService;
import com.example.sportclopedia.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
    UserService userService;

    @Test
    void testRegistrationPageShown() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testLoginPageShown() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }


    @Test
    void testUserRegistration() throws Exception {

        mockMvc.perform(post("/register")
                        .param("username", "Iliyan")
                        .param("fullName", "Iliyan Stavrev")
                        .param("email", "stavri@abv.bg")
                        .param("password", "123")
                        .param("confirmPassword", "123")
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sports"));

    }
}
