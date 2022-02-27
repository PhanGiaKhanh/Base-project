package com.demo.springsecuritydemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* @author: KhanhPG
* @since: 27/02/2022	
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() { 
		//	Xác thực người dùng thực hiện hoàn toàn ở AuthenticationProvider
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		//	Thực hiện validate thông tin người dùng 
		// 	DaoAuthenticationProvider sẽ call UserDetailService để xác thực thông tin người dùng
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		//	https://bcrypt-generator.com/
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/")
		.permitAll()
		.antMatchers("/home")
		.hasAnyAuthority("USER")
		.antMatchers("/admin")
		.hasAnyAuthority("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
}
