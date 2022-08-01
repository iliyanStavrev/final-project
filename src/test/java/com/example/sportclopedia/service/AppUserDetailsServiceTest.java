package com.example.sportclopedia.service;

import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.model.enums.UserRoleEnum;
import com.example.sportclopedia.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private AppUserDetailsService toTest;

    @BeforeEach
    void setUp(){
        toTest = new AppUserDetailsService(
                mockUserRepo
        );
    }

    @Test
     void testLoadUserByUsername_Exist(){
        User testUser = new User();
        testUser.setUsername("Georgi");
        testUser.setFullName("Georgi Georgiev");
        testUser.setPassword("123");
        testUser.setEmail("gergi@abv.bg");
        UserRole userRole = new UserRole();
        userRole.setUserRole(UserRoleEnum.USER);
        testUser.setUserRoles(
                List.of(userRole)
        );
        when(mockUserRepo.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        UserDetails userDetails =
                toTest.loadUserByUsername(testUser.getUsername());
        Assertions.assertEquals(testUser.getUsername(),userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(),userDetails.getPassword());
        Assertions.assertEquals(1,userDetails.getAuthorities().size());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),
                userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_NotExist(){

        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("does_not_exist@.com"));
    }
}
