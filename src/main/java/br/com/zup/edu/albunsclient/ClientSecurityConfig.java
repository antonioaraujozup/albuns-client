package br.com.zup.edu.albunsclient;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Component
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .csrf().disable()
                    .httpBasic().disable()
                    .rememberMe().disable()
                    .formLogin().disable()
                    .logout().disable()
                    .requestCache().disable()
                    .headers().frameOptions().deny()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(STATELESS)
                .and()
                    .authorizeRequests()
                        .anyRequest()
                            .permitAll();
    }
}
