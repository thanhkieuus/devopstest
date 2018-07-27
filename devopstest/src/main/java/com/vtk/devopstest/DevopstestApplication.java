package com.vtk.devopstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.vtk.devopstest.backend.persistence.repositories")
public class DevopstestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevopstestApplication.class, args);
	}
}
