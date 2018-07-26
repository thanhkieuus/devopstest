/**
 *
 */
package com.vtk.devopstest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author VK
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
	
	private static final String[] PUBLIC_MATCHES = {
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**/*"
	};
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info("+++++++++++++++++++ configure: enter");
		http
			.authorizeRequests()
			.antMatchers(PUBLIC_MATCHES).permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/payload")
			.failureUrl("/login?error").permitAll()
			.and()
			.logout().permitAll();
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("+++++++++++++++++++ configureGlobal: enter");
		auth
			.inMemoryAuthentication()
			.withUser("user")
			.password(passwordEncoder().encode("password"))
			.roles("USER");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
