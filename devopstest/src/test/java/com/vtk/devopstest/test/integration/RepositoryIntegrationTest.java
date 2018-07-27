/**
 *
 */
package com.vtk.devopstest.test.integration;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.backend.persistence.domain.backend.Plan;
import com.vtk.devopstest.backend.persistence.domain.backend.Role;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.persistence.repositories.PlanRepository;
import com.vtk.devopstest.backend.persistence.repositories.RoleRepository;
import com.vtk.devopstest.backend.persistence.repositories.UserRepository;
import com.vtk.devopstest.enums.PlansEnum;
import com.vtk.devopstest.enums.RolesEnum;
import com.vtk.devopstest.utils.UserUtils;



/**
 * @author VK
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryIntegrationTest {

	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void init() {
		Assert.assertNotNull(planRepository);
		Assert.assertNotNull(roleRepository);
		Assert.assertNotNull(userRepository);
	}
	
	
	@Test
	public void testCreateNewPlan() throws Exception {
		Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);
		Plan retrivedPlan = planRepository.findById(PlansEnum.BASIC.getId()).orElse(null);
		Assert.assertNotNull(retrivedPlan);
	}
	
	@Test
	public void testCreateNewRole() throws Exception {
		Role basicRole = createBasicRole(RolesEnum.BASIC);
		roleRepository.save(basicRole);
		Role retrivedRole = roleRepository.findById(RolesEnum.BASIC.getId()).orElse(null);
		Assert.assertNotNull(retrivedRole);
	}
	
	@Test
	public void testCreateNewUser() {
		
		Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);
		
		User basicUser = UserUtils.createBasicUser();
		basicUser.setPlan(basicPlan);
		
		Role basicRole = createBasicRole(RolesEnum.BASIC);
		
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);
		
		basicUser.getUserRoles().addAll(userRoles);
		
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		basicUser = userRepository.save(basicUser);
		
		User retriveUser = userRepository.findById(basicUser.getId()).orElse(null);
		
		Assert.assertNotNull(retriveUser);
		Assert.assertTrue(retriveUser.getId() != 0);
		Assert.assertNotNull(retriveUser.getPlan());
		Assert.assertNotNull(retriveUser.getPlan().getId());
		
		Set<UserRole> retrieveUserRoles = retriveUser.getUserRoles();
		for (UserRole ur : retrieveUserRoles) {
			Assert.assertNotNull(ur.getRole());
			Assert.assertNotNull(ur.getRole().getId());			
		}
		
	}

	/* private section */


	/**
	 * @return
	 */
	private Plan createBasicPlan(PlansEnum plansEnum) {
		Plan plan = new Plan(plansEnum);
		return plan;
	}


	/**
	 * @return
	 */
	private Role createBasicRole(RolesEnum rolesEnum) {
		Role role = new Role(rolesEnum);
		return role;
	}

	/**
	 * 
	 */
	public RepositoryIntegrationTest() {
		// TODO Auto-generated constructor stub
	}

}
