/**
 *
 */
package com.marco.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marco.controller.AdminController;
import com.marco.controller.SchedulerController;
import com.marco.service.CalendarBookRepository;

/**
 * @author marco.rotondi
 *
 */
@Configuration
public class MyBookingTestConfiguration {

	@Bean
	public CalendarBookRepository calendarBookRepo() {
		return Mockito.mock(CalendarBookRepository.class);
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
