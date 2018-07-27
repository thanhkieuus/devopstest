/**
 *
 */
package com.vtk.devopstest.backend.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtk.devopstest.backend.persistence.domain.backend.Role;

/**
 * @author VK
 *
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
