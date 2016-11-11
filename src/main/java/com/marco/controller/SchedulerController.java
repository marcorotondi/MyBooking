/**
 *
 */
package com.marco.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
		List<SchedulerMappingData> schedulerData = schedulerService.findAllResource();

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

	@RequestMapping(value = "/resources", method = RequestMethod.GET)
	public ResponseEntity<List<SchedulerMappingData>> retriveResource() {
		List<SchedulerMappingData> schedulerData = schedulerService.findAllResource();

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

	@RequestMapping(value = "/appointment/create", method = RequestMethod.POST)
	public ResponseEntity<SchedulerMappingData> createAppointment(@RequestBody @Valid SchedulerMappingData appoitment) {
		LOGGER.info("Try to create new appointment: {}", appoitment);

		LOGGER.info("Succesfully create new appointment: {}", appoitment.getId());
		return new ResponseEntity<>(appoitment, HttpStatus.OK);
	}

	@RequestMapping(value = "/appointment/update", method = RequestMethod.POST)
	public ResponseEntity<SchedulerMappingData> updateAppointment(@RequestBody @Valid SchedulerMappingData appoitment) {
		LOGGER.info("Try to update new appointment: {}", appoitment);

		LOGGER.info("Succesfully update new appointment: {}", appoitment.getId());
		return new ResponseEntity<>(appoitment, HttpStatus.OK);
	}

	@RequestMapping(value = "/appointment/delete/{appointmentId}", method = RequestMethod.DELETE)
	public ResponseEntity<SchedulerMappingData> deleteAppointment(@PathVariable("appointmentId") long appoitmentId) {
		LOGGER.info("Fetching & Deleting Appoitment with id: {}", appoitmentId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
