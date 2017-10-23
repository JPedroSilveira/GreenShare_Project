package com.seedshare.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.seedshare.entity.user.User;

/**
 * Authentication class for User
 * @author joao.silva
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${social.security.public:/user/register/}") 
    private String[] securityPublicUserRegister;
    
	@Autowired
	private UserSecurityService userDetailsService;
    
	@Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf().disable();
    }
	@Override
	public void configure(WebSecurity webSecurity) throws Exception {
	   webSecurity.ignoring()
	           .antMatchers(securityPublicUserRegister);
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("PUT", "DELETE","POST","GET");
            }
        };
    }
	
	@Autowired
    public void setDetailsService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(User.passwordEncoder());
    }
	
}
