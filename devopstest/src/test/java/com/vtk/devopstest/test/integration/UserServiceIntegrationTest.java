/**
 *
 */
package com.vtk.devopstest.test.integration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.backend.persistence.domain.backend.User;

/**
 * @author VK
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest{
	
	@Rule
	public TestName testName = new TestName();
	
	@Test
	public void testCreateNewUser() {
		
		User user = createUser(testName);
		
		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getId());
	}

}
