/**
 *
 */
package com.vtk.devopstest.test.integration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.backend.persistence.domain.backend.PasswordResetToken;
import com.vtk.devopstest.backend.persistence.domain.backend.Plan;
import com.vtk.devopstest.backend.persistence.domain.backend.Role;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.domain.backend.UserRole;
import com.vtk.devopstest.backend.persistence.repositories.PasswordResetTokenRepository;
import com.vtk.devopstest.backend.persistence.repositories.PlanRepository;
import com.vtk.devopstest.backend.persistence.repositories.RoleRepository;
import com.vtk.devopstest.backend.persistence.repositories.UserRepository;
import com.vtk.devopstest.enums.PlansEnum;
import com.vtk.devopstest.enums.RolesEnum;
import com.vtk.devopstest.utils.UserUtils;



/**
 * @author VK
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordResetTokenIntegrationTest extends AbstractIntegrationTest{

	@Value("${token.expiration.length.minutes}")
	private int expirationTimeInMinutes;
	
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Rule public TestName testName = new TestName();
	
	@Before
	public void init() {
		Assert.assertFalse(expirationTimeInMinutes == 0);
	}
	
	@Test
	public void testTokenExpirationLength() throws Exception {
		
		User user = createUser(testName);
		Assert.assertNotNull(user);
		Assert.assertTrue(user.getId() != 0);
		
		String token = UUID.randomUUID().toString();
		System.out.println("++++++++++ testTokenExpirationLength: token = " + token);

		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
		System.out.println("++++++++++ testTokenExpirationLength: now = " + now.toLocalTime());

		LocalDateTime expectedTime = now.plusMinutes(expirationTimeInMinutes);
		System.out.println("++++++++++ testTokenExpirationLength: expectedTime = " + expectedTime.toLocalTime());
		
		PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
		System.out.println("++++++++++ testTokenExpirationLength: passwordResetToken = " + passwordResetToken);
		
		LocalDateTime actualTime = passwordResetToken.getExpiryDate();
		System.out.println("++++++++++ testTokenExpirationLength: actualTime = " + actualTime);
		
		Assert.assertNotNull(actualTime);
		Assert.assertEquals(expectedTime, actualTime);				
	}

	@Test
	public void testFindTokenByTokenValue() throws Exception {
		
		User user = createUser(testName);
		
		String token = UUID.randomUUID().toString();
		System.out.println("++++++++++ testFindTokenByTokenValue: token = " + token);
		
		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
		System.out.println("++++++++++ testFindTokenByTokenValue: now = " + now.toLocalTime());
		
		createPasswordResetToken(token, user, now);
		PasswordResetToken retrievePasswordResetToken = passwordResetTokenRepository.findByToken(token);  
		
		Assert.assertNotNull(retrievePasswordResetToken);
		Assert.assertNotNull(retrievePasswordResetToken.getId());
		Assert.assertNotNull(retrievePasswordResetToken.getUser());
	}

	@Test
	public void testDeleteToken() throws Exception {
		
		User user = createUser(testName);
		String token = UUID.randomUUID().toString();
		System.out.println("++++++++++ testDeleteToken: token = " + token);
		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

		PasswordResetToken passwordResetToken = createPasswordResetToken(token, user, now);
		long tokenId = passwordResetToken.getId();
		
		PasswordResetToken shouldExistToken = passwordResetTokenRepository.findById((tokenId));  
		Assert.assertNotNull(shouldExistToken);

		passwordResetTokenRepository.deleteById(tokenId);
		
		PasswordResetToken shouldNotExistToken = passwordResetTokenRepository.findById((tokenId));  
		
		Assert.assertNull(shouldNotExistToken);
	}

	@Test
	public void testCascaseDeleteFromUserEntity() throws Exception {
		
		User user = createUser(testName);
		String token = UUID.randomUUID().toString();
		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

		createPasswordResetToken(token, user, now);
		
		userRepository.deleteById(user.getId());
		
		Set<PasswordResetToken> shouldBeEmpty = passwordResetTokenRepository.findAllByUserId(user.getId());  
		
		Assert.assertTrue(shouldBeEmpty.isEmpty());
	}

	   	@Test
	    public void testMultipleTokensAreReturnedWhenQueringByUserId() throws Exception {

	        User user = createUser(testName);
	        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());

	        String token1 = UUID.randomUUID().toString();
	        String token2 = UUID.randomUUID().toString();
	        String token3 = UUID.randomUUID().toString();

	        Set<PasswordResetToken> tokens = new HashSet<>();
	        tokens.add(createPasswordResetToken(token1, user, now));
	        tokens.add(createPasswordResetToken(token2, user, now));
	        tokens.add(createPasswordResetToken(token3, user, now));

	        passwordResetTokenRepository.saveAll(tokens);

	        User founduser = userRepository.findById(user.getId()).orElse(null);

	        Set<PasswordResetToken> actualTokens = passwordResetTokenRepository.findAllByUserId(founduser.getId());
	        
	        Assert.assertTrue(actualTokens.size() == tokens.size());
	        List<String> tokensAsList = tokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());
			System.out.println("++++++++++ testMultipleTokensAreReturnedWhenQueringByUserId: tokensAsList = " + tokensAsList);

	        List<String> actualTokensAsList = actualTokens.stream().map(prt -> prt.getToken()).collect(Collectors.toList());
			System.out.println("++++++++++ testMultipleTokensAreReturnedWhenQueringByUserId: actualTokensAsList = " + actualTokensAsList);        
	        
			Assert.assertEquals(tokensAsList, actualTokensAsList);

	    }
	
	/**
	 * @param token
	 * @param user
	 * @param now
	 * @return
	 */
	private PasswordResetToken createPasswordResetToken(String token, User user, LocalDateTime now) {
		// TODO Auto-generated method stub
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, now, expirationTimeInMinutes);
		passwordResetTokenRepository.save(passwordResetToken);
		Assert.assertNotNull(passwordResetToken.getId());
		return passwordResetToken;
	}
}
