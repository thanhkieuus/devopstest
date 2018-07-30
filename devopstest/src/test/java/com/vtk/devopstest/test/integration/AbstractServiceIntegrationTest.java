/**
 *
 */
package com.vtk.devopstest.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.vtk.devopstest.backend.persistence.domain.backend.Role;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.service.UserService;
import com.vtk.devopstest.enums.PlansEnum;
import com.vtk.devopstest.enums.RolesEnum;
import com.vtk.devopstest.utils.UserUtils;

/**
 * @author VK
 *
 */
public abstract class AbstractServiceIntegrationTest {

	@Autowired
	protected UserService userService;

	protected User createUser(TestName testName) {
			
		//Role role = new Role (RolesEnum.BASIC);	
		Set<UserRole> userRoles = new HashSet<>();
		User user = UserUtils.createBasicUser(testName);
		userRoles.add(new UserRole(user, new Role(RolesEnum.BASIC)));
		return userService.createUser(user, PlansEnum.BASIC, userRoles);
	}
	

}
