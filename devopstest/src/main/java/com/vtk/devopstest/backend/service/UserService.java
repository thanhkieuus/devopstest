/**
 *
 */
package com.vtk.devopstest.backend.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly=true)
public class UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private UserRepository userRepository;


	@Transactional
	public User createUser(User user, PlansEnum plansEnum, Set<UserRole> userRoles) {
		
		LOG.info("********** createUser: enter");

		Plan plan = new Plan(plansEnum);
		if (!planRepository.existsById(plan.getId())) {
			LOG.info("********** createUser: plan not existed.");
			plan = planRepository.save(plan);
		}
		user.setPlan(plan);
		
		for (UserRole userRole : userRoles) {
			roleRepository.save(userRole.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		user = userRepository.save(user);
		
		LOG.info("********** createUser: exit");
		return user;
	}
}
