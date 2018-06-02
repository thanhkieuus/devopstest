/**
 *
 */
package com.vtk.devopstest.config;

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

}
