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

	public static  User createBasicUser() {
		
		User user = new User();
		user.setUsername("user");
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

	
}
