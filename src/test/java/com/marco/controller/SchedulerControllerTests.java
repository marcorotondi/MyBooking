/**
 *
 */
package com.marco.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.marco.MybookingApplicationTests;
import com.marco.service.ResourceRepository;

/**
 * @author marco.rotondi
 *
 */
public class SchedulerControllerTests extends MybookingApplicationTests {

	@Autowired
	private SchedulerController schedulerController;

	@Autowired
	private ResourceRepository resourceRepo;

	@Before
	public void setUp() {

	}

	@Test
	public void testCreateScheduler() {

	}

	@Test
	public void testDeleteScheduler() {

	}

}
