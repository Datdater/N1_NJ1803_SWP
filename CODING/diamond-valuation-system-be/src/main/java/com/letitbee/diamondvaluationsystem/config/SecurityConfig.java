//package com.letitbee.diamondvaluationsystem.config;
//
//import com.letitbee.diamondvaluationsystem.security.JwtAuthenticationEntryPoint;
//import com.letitbee.diamondvaluationsystem.security.JwtAuthenticationFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableMethodSecurity
//public class SecurityConfig {
//    private UserDetailsService userDetailsService;
//
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.userDetailsService = userDetailsService;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
//        return configuration.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests((authorize) ->
//                        authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
//                                .requestMatchers("/api/v1/accounts/**").permitAll()
//                                .anyRequest().authenticated()
//                ).exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                ).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                ).httpBasic(Customizer.withDefaults());
//
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//}