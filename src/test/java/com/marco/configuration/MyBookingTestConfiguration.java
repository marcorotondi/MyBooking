/**
 *
 */
package com.marco.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.marco.controller.AdminController;
import com.marco.controller.SchedulerController;
import com.marco.service.CalendarBookRepository;
import com.marco.service.ResourceRepository;
import com.marco.service.SchedulerService;

/**
 * @author marco.rotondi
 *
 */
@TestConfiguration
public class MyBookingTestConfiguration {

	@Bean
	public CalendarBookRepository calendarBookRepo() {
		return Mockito.mock(CalendarBookRepository.class);
	}

	@Bean
	public ResourceRepository resourceRepo() {
		return Mockito.mock(ResourceRepository.class);
	}

	@Bean
	public SchedulerService schedulerService() {
		return Mockito.mock(SchedulerService.class);
	}

	@Bean
	public AdminController adminController() {
		return new AdminController();
	}

	@Bean
	public SchedulerController schedulerController() {
		return new SchedulerController();
	}

}
