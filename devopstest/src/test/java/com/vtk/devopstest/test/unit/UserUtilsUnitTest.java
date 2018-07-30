/**
 *
 */
package com.vtk.devopstest.test.unit;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.vtk.devopstest.utils.UserUtils;
import com.vtk.devopstest.web.controllers.ForgotMyPasswordController;


/**
 * @author VK
 *
 */
public class UserUtilsUnitTest {

	
	private MockHttpServletRequest mockHttpServletRequest;

	/**
	 * 
	 */
	@Before
	public void ini() {
		mockHttpServletRequest =  new MockHttpServletRequest();
	}
	
	/**
	 * 
	 */
	@Test
	public void testPasswordResetEmaiUrlContruction() throws Exception {
		
		System.out.println("++++++++++ UserUtilsUnitTest.testPasswordResetEmaiUrlContruction: enter");
		
		mockHttpServletRequest.setServerPort(8080);
		
		String token = UUID.randomUUID().toString();
		long userId = 12345;
		
		String expectedUrl = "http://localhost:8080" +
							 ForgotMyPasswordController.CHANGE_PASSWORD_PATH + 
							 "?id=" + 
							 userId + 
							 "&token=" + 
							 token;
		
		String actualUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token);

		System.out.println("++++++++++ UserUtilsUnitTest.testPasswordResetEmaiUrlContruction: expectedUrl = ");
		System.out.println("++++++++++ " + expectedUrl);
		
		System.out.println("++++++++++ UserUtilsUnitTest.testPasswordResetEmaiUrlContruction: actualUrl = ");
		System.out.println("++++++++++ " + actualUrl);

		Assert.assertEquals(expectedUrl, actualUrl);
	}
}
