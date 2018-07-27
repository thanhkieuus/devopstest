/**
 *
 */
package com.vtk.devopstest.backend.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vtk.devopstest.backend.persistence.domain.backend.Plan;

/**
 * @author VK
 *
 */
@Repository
public interface PlanRepository extends CrudRepository<Plan, Integer> {

}
