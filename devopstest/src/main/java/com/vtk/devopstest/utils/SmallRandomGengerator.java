/**
 *
 */
package com.vtk.devopstest.utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author VK
 *
 */
public  class SmallRandomGengerator {

	private static final int RANDOM_RANGE = 1000;

	
	public static int getARandomNumber() {
		
		ArrayList<Integer> randomNumbers = new ArrayList<>(RANDOM_RANGE);

		for (int i = 0; i<= RANDOM_RANGE; i++) {                
			randomNumbers.add(i);
        }

        Collections.shuffle(randomNumbers);
        int num = randomNumbers.get(0);
        System.out.println("********** SmallRandomGengerator.getARandomNumber: num = " + num);

        if (num == 1000) num--;
        Collections.shuffle(randomNumbers);

        return randomNumbers.get(num);
	}
	
	
}

