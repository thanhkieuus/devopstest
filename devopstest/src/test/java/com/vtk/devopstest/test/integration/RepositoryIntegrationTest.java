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
	
	private static final int BASIC_PLAN_ID = 1;
	private static final int BASIC_ROLE_ID = 1;
	
	@Before
	public void init() {
		Assert.assertNotNull(planRepository);
		Assert.assertNotNull(roleRepository);
		Assert.assertNotNull(userRepository);
	}
	
	
	@Test
	public void testCreateNewPlan() throws Exception {
		Plan basicPlan = createBasicPlan();
		planRepository.save(basicPlan);
		Plan retrivedPlan = planRepository.findById(BASIC_PLAN_ID).orElse(null);
		Assert.assertNotNull(retrivedPlan);
	}
	
	@Test
	public void testCreateNewRole() throws Exception {
		Role basicRole = createBasicRole();
		roleRepository.save(basicRole);
		Role retrivedRole = roleRepository.findById(BASIC_ROLE_ID).orElse(null);
		Assert.assertNotNull(retrivedRole);
	}
	
	@Test
	public void testCreateNewUser() {
		
		Plan basicPlan = createBasicPlan();
		planRepository.save(basicPlan);
		
		User basicUser = createBasicUser();
		basicUser.setPlan(basicPlan);
		
		Role basicRole = createBasicRole();
		
		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setUser(basicUser);
		userRole.setRole(basicRole);
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
	private Plan createBasicPlan() {
		Plan plan = new Plan();
		plan.setId(BASIC_PLAN_ID);
		plan.setName("Basic");
		return plan;
	}


	/**
	 * @return
	 */
	private Role createBasicRole() {
		Role role = new Role();
		role.setId(BASIC_ROLE_ID);
		role.setName("ROLE_USER");;
		return role;
	}

	private User createBasicUser() {
		
		User user = new User();
		user.setUsername("basicuser");
		user.setPassword("password");
		user.setEmail("mail@yahoo.com");
		user.setFirstName("firstname");
		user.setLastName("lastname");
		user.setPhoneNumber("123456789");
		user.setCountry("USA");
		user.setEnabled(true);
		user.setDescription("A basic user");
		user.setProfileImageUrl("http://haha.com");
		return user;
	}

	/**
	 * 
	 */
	public RepositoryIntegrationTest() {
		// TODO Auto-generated constructor stub
	}

}
