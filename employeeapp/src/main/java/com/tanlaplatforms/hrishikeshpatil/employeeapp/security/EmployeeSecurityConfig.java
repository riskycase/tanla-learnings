package com.tanlaplatforms.hrishikeshpatil.employeeapp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmployeeSecurityConfig {

    @Bean
    public UserDetailsManager getUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(customiser -> customiser
                .requestMatchers("/modify-employee/**").hasRole("MANAGER")
                .requestMatchers("/delete-employee/**").hasRole("ADMIN")
                .anyRequest().authenticated());
        httpSecurity.formLogin(
                customiser -> customiser.loginPage("/login").loginProcessingUrl("/login").permitAll());
        httpSecurity.logout(customiser -> customiser.logoutUrl("/logout").permitAll());
        httpSecurity.exceptionHandling(customiser -> customiser.accessDeniedPage("/access-denied"));

        return httpSecurity.build();
    }

}
