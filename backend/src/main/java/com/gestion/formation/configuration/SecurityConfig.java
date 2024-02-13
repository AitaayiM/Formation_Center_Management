package com.gestion.formation.configuration;


import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.gestion.formation.entity.Role;
import com.gestion.formation.repository.UserRepository;
import com.gestion.formation.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        
        mailSender.setUsername("majdaitaayi@gmail.com");
        mailSender.setPassword("uahc cxvc mdct nxxy");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize ->
                    authorize
                    .requestMatchers("/auth/signin",
                    "/auth/role**",
                    "/formations/all",
                    "/inscriptions/individu**",
                    "/formateurs/formations-interessees**",
                    "/evaluation/validate**",
                    "/review**",
                    "/formations/admin",
                    "/auth/admin/signup**",
                    "/admin/entreprises",
                    "/admin/planifications",
                    "/formateurs/all",
                    "/admin/groupe/affecter-formateur**",
                    "/inscriptions/allindividu",
                    "/admin/planifications/details",
                    "/admin/sendemail**")
                    .permitAll()
        );
        

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
 
    @Bean
    public UserDetailsService multipleUsers() {
       List<com.gestion.formation.entity.User> users = userRepository.findAll();;
       UserBuilder user = User.withDefaultPasswordEncoder();
       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
       for (com.gestion.formation.entity.User User : users) {
            Iterator<Role> roles= User.getRoles().iterator();
            while (roles.hasNext()) {
                String role = roles.next().getName();
                switch (role) {
                    case "ROLE_ADMIN":
                        manager.createUser(user.username(User.getUsername()).password(User.getPassword()).roles("ADMIN").build());
                        break;
                    case "ROLE_FORMATEUR":
                        manager.createUser(user.username(User.getUsername()).password(User.getPassword()).roles("FORMATEUR").build());
                        break;
                    case "ROLE_ASSISTANT":
                        manager.createUser(user.username(User.getUsername()).password(User.getPassword()).roles("ASSISTANT").build());
                        break;
                    default:
                        break;
                }
            }
        }
     
     return manager;
    }
 
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }



    
}