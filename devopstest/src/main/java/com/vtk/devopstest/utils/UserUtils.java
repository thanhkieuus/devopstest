/**
 *
 */
package com.vtk.devopstest.utils;

import org.junit.rules.TestName;

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
		
		System.out.println("++++++++++");
		System.out.println("++++++++++ UserUtils.createBasicUser: username and email:" + " " + username + ", " + email);
		System.out.println("++++++++++");
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

	public static User createBasicUser(TestName testName) {		
		return createBasicUser(testName.getMethodName() + SmallRandomGengerator.getARandomNumber(), testName.getMethodName()+"@devopstest.com");
	}

	
}
