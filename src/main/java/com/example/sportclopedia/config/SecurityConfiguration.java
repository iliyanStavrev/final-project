package com.example.sportclopedia.config;

import com.example.sportclopedia.model.enums.UserRoleEnum;
import com.example.sportclopedia.repository.UserRepository;
import com.example.sportclopedia.service.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/", "/register", "/login", "/sports", "/sports/api/all")
                .permitAll()
                .antMatchers("/trainings/add", "/halls/add","/coaches/add","/statistic")
                .hasRole(UserRoleEnum.ADMIN.name())
                .anyRequest().authenticated()
           .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/trainings/all")
                .failureForwardUrl("/login-error")
           .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {

        return new AppUserDetailsService(userRepository);
    }
}
