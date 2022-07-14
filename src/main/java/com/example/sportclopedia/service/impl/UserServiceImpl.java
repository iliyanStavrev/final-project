package com.example.sportclopedia.service.impl;

import com.example.sportclopedia.error.DuplicateEntryException;
import com.example.sportclopedia.model.dto.RegisterDto;
import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.model.enums.UserRoleEnum;
import com.example.sportclopedia.repository.UserRepository;
import com.example.sportclopedia.repository.UserRoleRepository;
import com.example.sportclopedia.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserDetailsService appUserDetailsService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    public void init() {
        if (userRepository.count() == 0 && userRoleRepository.count() == 0){

            UserRole adminRole = new UserRole();
            adminRole.setUserRole(UserRoleEnum.ADMIN);

            UserRole userRole = new UserRole();
            userRole.setUserRole(UserRoleEnum.USER);

            adminRole = userRoleRepository.save(adminRole);
            userRole = userRoleRepository.save(userRole);

            initAdmin(List.of(adminRole, userRole));
            initUser(List.of(userRole));

        }

    }

    public void initUser(List<UserRole> roles){

        User user = new User();
        user.setUserRoles(roles);
        user.setUsername("peter11");
        user.setFullName("Peter Petrov");
        user.setEmail("peter@abv.bg");
        user.setPassword(passwordEncoder.encode("123"));

        userRepository.save(user);
    }

    public void initAdmin(List<UserRole> roles){

        User admin = new User();
        admin.setUserRoles(roles);
        admin.setUsername("stavrev_11");
        admin.setFullName("Iliyan Stavrev");
        admin.setEmail("test@abv.bg");
        admin.setPassword(passwordEncoder.encode("123"));

        userRepository.save(admin);
    }

    @Override
    public void registerAndLogin(RegisterDto registerDto) {

        User userByUsername = userRepository
                .findByUsername(registerDto.getUsername())
                .orElse(null);

        if (userByUsername != null){
            throw new DuplicateEntryException(registerDto.getUsername());
        }

        User userByEmail = userRepository
                .findByEmail(registerDto.getEmail())
                .orElse(null);

        if (userByEmail != null){
            throw new DuplicateEntryException(registerDto.getEmail());
        }

        UserRole userRole = new UserRole();
        userRole.setUserRole(UserRoleEnum.USER);

        User user = modelMapper
                .map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setUserRoles(List.of(userRole));

        userRepository.save(user);

        UserDetails userDetails =
                appUserDetailsService.loadUserByUsername(user.getUsername());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );
        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);
    }
}
