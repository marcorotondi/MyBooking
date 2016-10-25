/**
 *
 */
package com.marco.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.model.Resource;
import com.marco.type.ResourceType;

/**
 * @author Marco
 *
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	long countByType(ResourceType type);
	
	long count();
	
	List<Resource> findAllOrderByTypeDescAndDescriptionDesc();
}
