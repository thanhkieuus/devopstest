/**
 *
 */
package com.vtk.devopstest.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testCreateNewUser() {
		
		Set<UserRole> userRoles = new HashSet<>();
		User basicUser = UserUtils.createBasicUser();
		userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));
		
		User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
	}

}
