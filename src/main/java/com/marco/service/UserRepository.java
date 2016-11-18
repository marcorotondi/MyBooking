/**
 *
 */
package com.marco.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marco.model.User;

/**
 * @author Marco
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(final String email);

}
