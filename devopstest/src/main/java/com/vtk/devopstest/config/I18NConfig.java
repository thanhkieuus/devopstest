/**
 *
 */
package com.vtk.devopstest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author VK
 *
 */
@Configuration
public class I18NConfig {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("classpath:i18n/messages");

		// Check out for new message every 30 minutes
		bundleMessageSource.setCacheSeconds(1800);
		return bundleMessageSource;
	}

}
