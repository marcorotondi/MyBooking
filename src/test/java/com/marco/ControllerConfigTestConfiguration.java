/**
 * 
 */
package com.marco;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;

import com.marco.controller.AdminController;
import com.marco.service.ResourceRepository;

/**
 * @author marco.rotondi
 *
 */
public class ControllerConfigTestConfiguration {
	
	@Bean
	public ResourceRepository resourceRepo() {
		return Mockito.mock(ResourceRepository.class);
	}
	
	@Bean
	public AdminController adminController() {
		return new AdminController();
	}

}
