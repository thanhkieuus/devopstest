/**
 *
 */
package com.vtk.devopstest.enums;

/**
 * @author VK
 *
 */
public enum PlansEnum {
	
	BASIC(1, "Baisc"),
	PRO(2, "Pro");
	
	private final int id;
	private final String planName;
	
	private PlansEnum(int id, String planName) {
		this.id = id;
		this.planName = planName;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the roleName
	 */
	public String getPlanName() {
		return planName;
	}
	
}
