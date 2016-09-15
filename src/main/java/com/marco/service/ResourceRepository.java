/**
 *
 */
package com.marco.service;

import org.springframework.data.repository.CrudRepository;

import com.marco.model.Resource;

/**
 * @author Marco
 *
 */
public interface ResourceRepository extends CrudRepository<Resource, Long> {

}
