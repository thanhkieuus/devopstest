/**
 *
 */
package com.vtk.devopstest.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.vtk.devopstest.backend.service.EmailService;
import com.vtk.devopstest.backend.service.MockEmailService;

/**
 * @author VK
 *
 */
@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/devopstest/application-dev.properties")
public class DevelopmentConfig {

	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

	@Bean
	public ServletRegistrationBean<WebServlet> h2ConsoleServletRegistration( ) {
		ServletRegistrationBean<WebServlet> bean = new ServletRegistrationBean<>(new WebServlet(), "/console/*");
		return bean;
	}

}
