/**
 *
 */
package com.vtk.devopstest.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author VK
 *
 */
public class Authority implements GrantedAuthority {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private final String authority;

	/**
	 * 
	 */
	public Authority(String authority) {
		this.authority = authority;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	@Override
	public String getAuthority() {
		return authority;
	}

}
