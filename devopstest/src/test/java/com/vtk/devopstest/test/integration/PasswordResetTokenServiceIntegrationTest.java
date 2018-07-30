/**
 *
 */
package com.vtk.devopstest.test.integration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.backend.persistence.domain.backend.PasswordResetToken;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.service.PasswordResetTokenService;

/**
 * @author VK
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordResetTokenServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Rule public TestName testName = new TestName();

	/**
	 * 
	 */
	@Test
	public void TestCreateNewTokenForUserEmail() {
		
		User user = createUser(testName);
		PasswordResetToken PRToken = passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
		
		Assert.assertNotNull(PRToken);
		Assert.assertNotNull(PRToken.getToken());
	}

	/**
	 * 
	 */
	@Test
	public void TestFindByToken() {
		
		User user = createUser(testName);
		PasswordResetToken PRToken = passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
		
		Assert.assertNotNull(PRToken);
		Assert.assertNotNull(PRToken.getToken());

		PasswordResetToken retrievePRToken = passwordResetTokenService.findByToken(PRToken.getId());
		Assert.assertNotNull(retrievePRToken);
		Assert.assertNotNull(retrievePRToken.getToken());
	}

}
