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

import com.marco.data.CalendarData;
import com.marco.service.SchedulerService;

/**
 * @author marco.rotondi
 *
 */
@RestController
public class SchedulerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerController.class);

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value = "/public/api/schedulers", method = RequestMethod.GET)
	public ResponseEntity<List<CalendarData>> retriveScheduler() {
		List<CalendarData> schedulerData = schedulerService.findAllByRangeDate(LocalDateTime.now(), null);

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

}
