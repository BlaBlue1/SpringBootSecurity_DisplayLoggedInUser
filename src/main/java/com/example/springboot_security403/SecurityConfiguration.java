package com.example.springboot_security403;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/teacher").hasRole("ADMIN")
                .antMatchers("/student").hasRole("USER")
                .antMatchers("/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers().frameOptions().sameOrigin();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws
    Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("teacher")
                .password(passwordEncoder().encode("teacher"))
                .roles("ADMIN")
                .and()
                .withUser("student").password(passwordEncoder().encode("student"))
                .roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin"))
                .roles("ADMIN","USER");

    }
}
