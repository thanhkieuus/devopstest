/**
 *
 */
package com.vtk.devopstest.enums;

/**
 * @author VK
 *
 */
public enum RolesEnum {
	
	BASIC(1, "ROLE_BASIC"),
	PRO(2, "ROLE_PRO"),
	ADMIN(3, "ROLE_ADMIN");
	
	private final int id;
	private final String roleName;
	
	private RolesEnum(int id, String roleName) {
		this.id = id;
		this.roleName = roleName;
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
	public String getRoleName() {
		return roleName;
	}
	
}
