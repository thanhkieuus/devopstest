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
<<<<<<< HEAD
		Role basicRole = createBasicRole(BASIC_ROLE_ID, "ROLE_USER");
=======
		Role basicRole = createBasicRole(RolesEnum.BASIC);
>>>>>>> jpa
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
		
<<<<<<< HEAD
		Role basicRole = createBasicRole(1, "ROLE_USER");
=======
		Role basicRole = createBasicRole(RolesEnum.BASIC);
>>>>>>> jpa
		
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);
		
		Role basicRole2 = createBasicRole(2, "ROLE_ADMIN");
		UserRole userRole2 = new UserRole();
		userRole2.setUser(basicUser);
		userRole2.setRole(basicRole2);
		userRoles.add(userRole2);
		
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
			System.out.println("******************* : " + ur);
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
<<<<<<< HEAD
	private Role createBasicRole(int id, String name) {
		Role role = new Role();
		role.setId(id);
		role.setName(name);
=======
	private Role createBasicRole(RolesEnum rolesEnum) {
		Role role = new Role(rolesEnum);
>>>>>>> jpa
		return role;
	}

	/**
	 * 
	 */
	public RepositoryIntegrationTest() {
		// TODO Auto-generated constructor stub
	}

}
