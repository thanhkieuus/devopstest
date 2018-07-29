/**
 *
 */
package com.vtk.devopstest.backend.persistence.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtk.devopstest.backend.persistence.domain.backend.PasswordResetToken;

/**
 * @author VK
 *
 */
@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
	
	PasswordResetToken findByToken(String token);
	
	@Query("select prt from PasswordResetToken prt inner join prt.user u where prt.user.id = ?1")
	Set<PasswordResetToken> findAllByUserId(long userId);

	/**
	 * @param l
	 * @return
	 */
	PasswordResetToken findById(long id);

}
