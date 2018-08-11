/**
 *
 */
package com.vtk.devopstest.utils;

import javax.servlet.http.HttpServletRequest;

import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.web.controllers.ForgotMyPasswordController;

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

	/**
	 * @param mockHttpServletRequest
	 * @param userId
	 * @param token
	 * @return
	 */
	public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
		
		String passwordResetUrl = 
				request.getScheme() +
						"://" +
						request.getServerName() +
						":" +
						request.getServerPort() +
						request.getContextPath() +
						ForgotMyPasswordController.CHANGE_PASSWORD_PATH +
						"?id=" +
						userId +
						"&token=" +
						token;
				
		
		return passwordResetUrl;
	}
	
}
