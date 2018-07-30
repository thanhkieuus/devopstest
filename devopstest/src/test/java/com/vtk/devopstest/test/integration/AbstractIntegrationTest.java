/**
 *
 */
package com.vtk.devopstest.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.vtk.devopstest.backend.persistence.domain.backend.Plan;
import com.vtk.devopstest.backend.persistence.domain.backend.Role;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.persistence.repositories.PlanRepository;
import com.vtk.devopstest.backend.persistence.repositories.RoleRepository;
import com.vtk.devopstest.backend.persistence.repositories.UserRepository;
import com.vtk.devopstest.backend.service.UserService;
import com.vtk.devopstest.enums.PlansEnum;
import com.vtk.devopstest.enums.RolesEnum;
import com.vtk.devopstest.utils.SmallRandomGengerator;
import com.vtk.devopstest.utils.UserUtils;

/**
 * @author VK
 *
 */
public abstract class AbstractIntegrationTest {

	@Autowired
	protected PlanRepository planRepository;
	
	@Autowired
	protected RoleRepository roleRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected UserService userService;
	

	/**
	 * @return
	 */
	protected Plan createPlan(PlansEnum plansEnum) {
		Plan plan = new Plan(plansEnum);
		return plan;
	}


	/**
	 * @return
	 */
	protected Role createRole(RolesEnum rolesEnum) {
		Role role = new Role(rolesEnum);
		return role;
	}

	
/**	
 		protected User createUser(String username, String email) {
		
		Plan basicPlan = createPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);
		
		User basicUser = UserUtils.createBasicUser(username, email);
		basicUser.setPlan(basicPlan);
		
		Role basicRole = createRole(RolesEnum.BASIC);
		roleRepository.save(basicRole);
		
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);
		
		basicUser.getUserRoles().addAll(userRoles);
		User localUser = userRepository.save(basicUser);
		
		System.out.println("++++++++++ AbstractIntegrationTest.createUser: Save user: " + localUser);
		return localUser;
	}
	


*/
	protected User createUser(String username, String email) {
			
		User user = UserUtils.createBasicUser(username, email);
		
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(user, new Role(RolesEnum.BASIC));
		userRoles.add(userRole);
		
		User createdUser = userService.createUser(user, PlansEnum.BASIC, userRoles);
		
		System.out.println("++++++++++ AbstractIntegrationTest.createUser: created user: " + createdUser);
		return createdUser;
	}

	
	
	
	protected User createUser(TestName testName) {
		return createUser(testName.getMethodName() + SmallRandomGengerator.getARandomNumber(), testName.getMethodName() + "@devoptest.com");
	}

	
	
	
}
