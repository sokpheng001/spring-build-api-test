package com.example.api.mbanking.security;

import com.example.api.mbanking.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final SecurityUtil securityUtil;
    // Define in-memory
    private final SecurityBean securityBean;
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager  = new InMemoryUserDetailsManager();
        //
        System.out.println("========================================================");
        System.out.println("Kim ChansokPheng: " + new BCryptPasswordEncoder().encode("Kim ChansokPheng"));
        System.out.println("========================================================");
        //
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("account","1234","ACCOUNT"));
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("account type","234","ACCOUNT_TYPE"));
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("notification","456","NOTIFICATION"));
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("admin","123","ADMIN"));
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("file","file123","FILE"));
        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("user account","123","USER_ACCOUNT"));
        return inMemoryUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().httpBasic();
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/users/**")
                .hasAnyRole("ADMIN");
        httpSecurity.authorizeHttpRequests()
                        .requestMatchers("a/pi/v1/user-accounts/**")
                                .hasRole("USER_ACCOUNT");
        httpSecurity.authorizeHttpRequests()
                        .requestMatchers("/api/v1/accounts/**")
                                .hasRole("ACCOUNT");
        httpSecurity.authorizeHttpRequests()
                        .requestMatchers("/api/v1/account-types/**")
                                .hasRole("ACCOUNT_TYPE");
        httpSecurity.authorizeHttpRequests()
                        .requestMatchers("/api/v1/notifications")
                                .hasRole("NOTIFICATION");
        httpSecurity.authorizeHttpRequests()
                        .requestMatchers("/api/v1/files/**")
                                .hasRole("FILE")
                                        .anyRequest().permitAll();
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
