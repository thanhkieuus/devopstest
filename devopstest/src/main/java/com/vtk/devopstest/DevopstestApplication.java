package com.vtk.devopstest;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vtk.devopstest.backend.persistence.domain.backend.Role;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.service.UserService;
import com.vtk.devopstest.enums.PlansEnum;
import com.vtk.devopstest.enums.RolesEnum;
import com.vtk.devopstest.utils.UserUtils;

@SpringBootApplication
public class DevopstestApplication implements CommandLineRunner{

	private static final Logger LOG = LoggerFactory.getLogger(DevopstestApplication.class);
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(DevopstestApplication.class, args);
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) throws Exception {

		LOG.info("********** run: enter");
		LOG.info("********** run: enter");
		LOG.info("********** run: enter");
		LOG.info("********** run: enter");
		LOG.info("********** run: enter");
		
		String username = "user1";
		String email = "user1@email.com";
		
		User user = UserUtils.createBasicUser(username, email);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.PRO)));
		userService.createUser(user, PlansEnum.PRO, userRoles);
	
	}
}
