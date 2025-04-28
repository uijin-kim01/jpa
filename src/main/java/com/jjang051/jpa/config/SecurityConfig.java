package com.jjang051.jpa.config;

import com.jjang051.jpa.service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //이러면 bean으로 등록되고 container에 등록된다. DI할 수 있다.

    private final OAuth2DetailsService oAuth2DetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                        (auth) ->
                                auth
                                        .requestMatchers(
                                                "/",
                                                "/index/index",
                                                "/member/signup",
                                                "/member/login",
                                                "/css/**",
                                                "/images/**",
                                                "/email/find-password",
                                                "/email/email-check"
                                        )
                                        .permitAll()
                                        .requestMatchers("/admin/**").hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated()

                )
                .csrf((CsrfConfigurer<HttpSecurity> auth) -> auth.disable())
                .formLogin(
                        (auth) ->
                                auth
                                        .loginPage("/member/login") // getMapping시 처리위치
                                        .usernameParameter("userID")  //login.html에 있는 input name="userID"
                                        .passwordParameter("userPW")
                                        .loginProcessingUrl("/member/login") // postMapping시 처리위치
                                        .defaultSuccessUrl("/", true)
                                        .failureUrl("/member/login-fail") // sendRedirect로 넘김.
                                        .permitAll()
                )
                .logout((auth) ->
                        auth.logoutUrl("/member/logout")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                );

        httpSecurity.oauth2Login((auth) ->
                auth
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfo->userInfo.userService(oAuth2DetailsService))
        );
        return httpSecurity.build();
    }
}
