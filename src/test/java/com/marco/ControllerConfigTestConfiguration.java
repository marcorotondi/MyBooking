/**
 * 
 */
package com.marco;

import org.springframework.context.annotation.Bean;

import com.marco.controller.AdminController;

/**
 * @author marco.rotondi
 *
 */
public class ControllerConfigTestConfiguration {
	
	@Bean
	public AdminController adminController() {
		return new AdminController();
	}

}
