/**
 *
 */
package com.vtk.devopstest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.vtk.devopstest.backend.service.EmailService;
import com.vtk.devopstest.backend.service.SmtpMailService;

/**
 * @author VK
 *
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/devopstest/application-prod.properties")
public class ProductionConfig {
	
	@Bean
	public EmailService emailService() {
		return new SmtpMailService();
	}

}
