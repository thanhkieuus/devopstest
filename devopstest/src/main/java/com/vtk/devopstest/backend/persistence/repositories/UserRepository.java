/**
 *
 */
package com.vtk.devopstest.backend.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtk.devopstest.backend.persistence.domain.backend.User;


/**
 * @author VK
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByUsername(String username);

}
