/**
 *
 */
package com.marco.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.marco.model.Resource;

/**
 * @author Marco
 *
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
