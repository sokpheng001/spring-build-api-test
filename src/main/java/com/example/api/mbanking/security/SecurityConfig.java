package com.example.api.mbanking.security;

import com.example.api.mbanking.util.SecurityUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final SecurityUtil securityUtil;
    // Define in-memory
    private final SecurityBean securityBean;
//    private final UserDetailsServiceImp userDetailsServiceImp;
    private final UserDetailsService userDetailsService;
    private final CustomerAuthenticationEntryPoint customerAuthenticationEntryPoint;
    //define user i memory
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager inMemoryUserDetailsManager  = new InMemoryUserDetailsManager();
//        //
//        System.out.println("========================================================");
//        System.out.println("Kim ChansokPheng: " + new BCryptPasswordEncoder().encode("Kim ChansokPheng"));
//        System.out.println("========================================================");
//        //
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("account","1234","ACCOUNT"));
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("account type","234","ACCOUNT_TYPE"));
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("notification","456","NOTIFICATION"));
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("admin","123","ADMIN"));
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("file","file123","FILE"));
//        inMemoryUserDetailsManager.createUser(securityUtil.userDetails("user account","123","USER_ACCOUNT"));
//        return inMemoryUserDetailsManager;
//    }
//    InMemoryUserDetailsManager;
//    UserDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService); //
        authenticationProvider.setPasswordEncoder(securityBean.encoder()); // we can use new BCryptPasswordEncoder();
        return authenticationProvider;
    }
//    @Bean
//    public UserDetailsService detailsService(){
//        UserDetails userDetails = User.builder()
//                .username("user")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .build();
//        //
//        UserDetails userDetails1 = User.builder()
//                .username("user")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .build();
//        //
//        UserDetails userDetails2 = User.builder()
//                .username("user")
//                .password("{noop}123")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails,userDetails1,userDetails2);
//    }
    //JWT
    @Bean
    public KeyPair keyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        System.out.println(keyPairGenerator.generateKeyPair().getPrivate());
        return keyPairGenerator.generateKeyPair();
    }
    @Bean
    public RSAKey rsaKey(KeyPair keyPair){
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }
    @Bean
    JWKSource<SecurityContext> jwkSource(RSAKey rsaKey){
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new JWKSource<SecurityContext>() {
            @Override
            public List<JWK> get(JWKSelector jwkSelector, SecurityContext context) throws KeySourceException{
                return jwkSelector.select(jwkSet);
            }
        };
    }
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);
    }
    // end of JWT config
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity
                .authorizeHttpRequests(http->{
                    http.requestMatchers(HttpMethod.GET,"/api/v1/users/**")
                            .hasAnyAuthority("SCOPE_user:read");
                    http.requestMatchers(HttpMethod.DELETE,"/api/v1/users/**")
                            .hasAnyAuthority("SCOPE_user:delete");
                    //account-type
                    http.requestMatchers(HttpMethod.GET,"/api/v1/account-types/**")
                            .hasAnyAuthority("SCOPE_user:read");
                    http.requestMatchers(HttpMethod.DELETE,"/api/v1/account-types/**")
                            .hasAnyAuthority("SCOPE_account:delete");
                });
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll();
        httpSecurity.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);//oauth2->oauth2.jwt())
        //        httpSecurity.authorizeHttpRequests()
//                        .requestMatchers("/api/v1/auth/**");
        //authenticate first before using
//        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        //don't need to authenticate before using just click request is ok.
        //exception
//        httpSecurity.exceptionHandling()
//                        .authenticationEntryPoint(customerAuthenticationEntryPoint);
        //
//        httpSecurity.exceptionHandling()
//                        .accessDeniedHandler();
        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
