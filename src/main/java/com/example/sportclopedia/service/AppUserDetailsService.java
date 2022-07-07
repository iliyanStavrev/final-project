package com.example.sportclopedia.service;

import com.example.sportclopedia.model.entity.User;
import com.example.sportclopedia.model.entity.UserRole;
import com.example.sportclopedia.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

      return userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException
                        ("User with username " + username + " not found!"));

    }

    private UserDetails map(User user) {

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getUserRoles()
                        .stream()
                        .map(this::map)
                        .collect(Collectors.toList()))
                .build();
    }

    private GrantedAuthority map(UserRole userRole){
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getUserRole().name());
    }

}
