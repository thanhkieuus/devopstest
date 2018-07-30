/**
 *
 */
package com.vtk.devopstest.test.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vtk.devopstest.utils.SmallRandomGengerator;

/**
 * @author VK
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmallRandomGeneratorTest {

	/**
	 * 
	 */
	@Test
	public void  TestSmallRandomGenerator() {
		int num1 = SmallRandomGengerator.getARandomNumber();
		int num2 = SmallRandomGengerator.getARandomNumber();
		
		System.out.println("SmallRandomGeneratorTest.TestSmallRandomGenerator: num1 and num2 = " + num1 + ", " + num2);
		Assert.assertTrue(num1 != num2);
	}
	
	

}
