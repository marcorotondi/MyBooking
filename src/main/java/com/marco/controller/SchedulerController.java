/**
 *
 */
package com.marco.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marco.data.SchedulerMappingData;
import com.marco.service.SchedulerService;

/**
 * @author marco.rotondi
 *
 */
@RestController
@RequestMapping(value = "/public/api")
public class SchedulerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value = "/schedulers", method = RequestMethod.GET)
	public ResponseEntity<List<SchedulerMappingData>> retriveScheduler() {
		List<SchedulerMappingData> schedulerData = schedulerService.findAllByRangeDate(LocalDateTime.now(), null);

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public ResponseEntity<List<SchedulerMappingData>> retriveResource() {
		List<SchedulerMappingData> schedulerData = schedulerService.findAllResource();

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

}
