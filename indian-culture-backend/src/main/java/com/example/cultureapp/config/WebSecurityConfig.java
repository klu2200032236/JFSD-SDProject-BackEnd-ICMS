//package com.example.cultureapp.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class WebSecurityConfig{
//	 @Bean
//	    public BCryptPasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//
//	    @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf().disable()
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/auth/**").permitAll()
//	                .anyRequest().authenticated()
//	            );
//	        return http.build();
//    }
//}



//package com.example.cultureapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//public class WebSecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll()
//                .anyRequest().authenticated()
//            );
//        return http.build();
//    }
//    
//    
//}












































//
//
//
//package com.example.cultureapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class WebSecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable CSRF for simplicity
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll() // Publicly accessible routes
//                .requestMatchers("/blogs/**").permitAll() // Allow anyone to access blogs
//                .anyRequest().authenticated() // Protect all other routes
//            )
//            .formLogin().disable() // Disable form login (you can enable it if needed)
//            .httpBasic().disable(); // Disable basic HTTP authentication
//
//        return http.build();
//    }
//}



































package com.example.cultureapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Publicly accessible routes
                .requestMatchers("/blogs/**").permitAll() // Allow anyone to access blogs
                .anyRequest().authenticated() // Protect all other routes
            )
            .formLogin().disable() // Disable form login (you can enable it if needed)
            .httpBasic().disable(); // Disable basic HTTP authentication

        return http.build();
    }

}

















