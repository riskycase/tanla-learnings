package com.tanlaplatforms.hrishikeshpatil.employeeapp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

        httpSecurity.csrf(customiser -> customiser.disable());
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(customiser -> customiser
                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
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
