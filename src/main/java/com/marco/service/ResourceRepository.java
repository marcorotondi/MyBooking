/**
 *
 */
package com.marco.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.model.Resource;
import com.marco.type.ResourceType;

/**
 * @author Marco
 *
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {
	
	Long countByType(ResourceType type);
}
