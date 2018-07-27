/**
 *
 */
package com.vtk.devopstest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author VK
 *
 */
@Configuration
@EnableJpaRepositories(basePackages="com.vtk.devopstest.backend.persistence.repositories")
@EntityScan(basePackages="com.vtk.devopstest.backend.persistence.domain.backend")
@EnableTransactionManagement
public class ApplicationConfig {
}
