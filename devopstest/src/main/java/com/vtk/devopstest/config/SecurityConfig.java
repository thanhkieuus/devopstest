/**
 *
 */
package com.vtk.devopstest.config;

import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	private Environment env;

	private static final String[] PUBLIC_MATCHES = { 
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**/*",
			"/console/**"
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOG.info("+++++++++++++++++++ configure: enter");
		
		List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
		if (activeProfiles.contains("dev")) {
			LOG.info("+++++++++++++++++++ configure: Dev profile");
			http.csrf().disable();
			http.headers().frameOptions().disable();
		}
		
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
	 * annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		LOG.info("+++++++++++++++++++ configureGlobal: enter");
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
