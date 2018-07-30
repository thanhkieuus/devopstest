/**
 *
 */
package com.vtk.devopstest.backend.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vtk.devopstest.backend.persistence.domain.backend.PasswordResetToken;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.repositories.PasswordResetTokenRepository;
import com.vtk.devopstest.backend.persistence.repositories.UserRepository;

/**
 * @author VK
 *
 */
@Service
public class PasswordResetTokenService {

	private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);
	
	@Value("${token.expiration.length.minutes}")
	private int expirationTimeInMinutes;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Create a new Password Reset Token for the user identifying by email.
	 * @param email The email uniquely identifying the user  
	 * @return a new Password Reset Token or null if non user was found.
	 */
	public PasswordResetToken createPasswordResetTokenForEmail(String email) {
		
		PasswordResetToken passwordResetToken = null;
		
		User user = userRepository.findByEmail(email);
		if (null != user) {
			String token = UUID.randomUUID().toString();
			LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
			passwordResetToken = new PasswordResetToken(token, user, now, expirationTimeInMinutes);
			passwordResetTokenRepository.save(passwordResetToken);
			LOG.debug("Successfully created token {} for user {}", token, user.getUsername());
		}
		else {
			LOG.warn("We could't fine user for the given email {}", email);
		}
		
		return passwordResetToken;
	}

	/**
	 * @param id
	 */
	public PasswordResetToken findByToken(long id) {

		return passwordResetTokenRepository.findById(id);
	}
	
}
