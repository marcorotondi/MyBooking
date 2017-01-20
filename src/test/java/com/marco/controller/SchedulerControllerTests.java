/**
 * 
 */
package com.marco.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.marco.configuration.ControllerConfigTestConfiguration;
import com.marco.service.ResourceRepository;

/**
 * @author marco.rotondi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ControllerConfigTestConfiguration.class})
public class SchedulerControllerTests {
	
	@Autowired
	private SchedulerController schedulerController;
	
	@Autowired
	private ResourceRepository resourceRepo;
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testVerifyCalendarOverlapping() {

	}

	@Test
	public void testCreateScheduler() {

	}

	@Test
	public void testDeleteScheduler() {

	}

}
