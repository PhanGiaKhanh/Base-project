package com.KhanhPG.WebNetFlix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.KhanhPG.WebNetFlix.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomerUserDetailService customerUserDetailService;
	
	 String[] resources = new String[]{
			 "/", "/home/**", "/css/**", "/js/**", "/webfonts/**"
     };
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() 
		.antMatchers(resources).permitAll()
		.anyRequest().authenticated().and()
		.formLogin().loginPage("/login").permitAll()
		.defaultSuccessUrl("/login?success=true")
		.failureUrl("/login?success=false")
		.loginProcessingUrl("/j_spring_security_check");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // class bảo mật Spring Security
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Lấy mật khẩu từ customerUserDetailService (mật khẩu nhập vào) chuyển thành mã bảo mật rồi so sánh
		auth.userDetailsService(customerUserDetailService).passwordEncoder(passwordEncoder());
	}
}
