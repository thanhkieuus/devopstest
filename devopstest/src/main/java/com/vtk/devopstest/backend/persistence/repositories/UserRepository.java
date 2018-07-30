/**
 *
 */
package com.vtk.devopstest.backend.persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vtk.devopstest.backend.persistence.domain.backend.User;


/**
 * @author VK
 *
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * Return a User given a username or null if not found
	 * @param username The username
	 * @return a User given a username or null if not found
	 */
	User findByUsername(String username);
	
	/**
	 * Return a User given a email or null if not found
	 * @param The user's email
	 * @return a User given a email or null if not found
	 */
	User findByEmail(String email);

	@Modifying
	@Query("update User u set u.password = :password where u.id = :userId")
	void updateUserPassword(@Param("userId") long userId, @Param("password") String password);

}
