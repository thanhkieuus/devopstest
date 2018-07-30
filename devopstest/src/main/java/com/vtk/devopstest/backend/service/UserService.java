/**
 *
 */
package com.vtk.devopstest.backend.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vtk.devopstest.backend.persistence.domain.backend.Plan;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.persistence.repositories.PlanRepository;
import com.vtk.devopstest.backend.persistence.repositories.RoleRepository;
import com.vtk.devopstest.backend.persistence.repositories.UserRepository;
import com.vtk.devopstest.enums.PlansEnum;

/**
 * @author VK
 *
 */
@Service
//@Transactional(readOnly=true)
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
		
		LOG.info("********** createUser: enter");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		

		Plan plan = new Plan(plansEnum);
		if (!planRepository.existsById(plansEnum.getId())) {
			plan = planRepository.save(plan);
		}
		user.setPlan(plan);
		
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		User localUser = userRepository.save(user);
		
		LOG.info("********** createUser: user created: " + localUser);
		return localUser;
	}
	
	public void updateUserPassword(long userId, String password) {
		password = passwordEncoder.encode(password);
		userRepository.updateUserPassword(userId, password);
		LOG.debug("Password updeded successfully for user id {}", userId);
	}

}
