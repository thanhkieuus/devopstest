/**
 *
 */
package com.vtk.devopstest.utils;

import com.vtk.devopstest.backend.persistence.domain.backend.User;

/**
 * @author VK
 *
 */
public class UserUtils {

	/**
	 * 
	 */
	private UserUtils() {
		throw new AssertionError("Non-instantiable");
	}

	public static User createBasicUser(String username, String email) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword("password");
		user.setEmail(email);
		user.setFirstName("firstname");
		user.setLastName("lastname");
		user.setPhoneNumber("123456789");
		user.setCountry("USA");
		user.setEnabled(true);
		user.setDescription("A basic user");
		user.setProfileImageUrl("http://haha.com");
		return user;
	}

	
}
