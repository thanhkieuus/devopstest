package com.vtk.devopstest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.web.i18n.I18NService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopstestApplicationTests {

	@Autowired
	private I18NService i18NService;
	
	
	@Test
	public void testMessageByLocalService() {
		String expected = "Bootstrap starter template";
		String messageId = "index.main.callout";
		String actual = i18NService.getMessage(messageId);
		Assert.assertEquals("The actual and expect Stringd don`t match", expected, actual);
	}

}
