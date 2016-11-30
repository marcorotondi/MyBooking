/**
 *
 */
package com.marco.controller;

import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@GetMapping(value = "/schedulers")
	public ResponseEntity<List<SchedulerMappingData>> retriveScheduler() {
		List<SchedulerMappingData> schedulerData = schedulerService.findAllScheduler();

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

	@GetMapping(value = "/resources")
	public ResponseEntity<List<SchedulerMappingData>> retriveResource() {
		List<SchedulerMappingData> schedulerData = schedulerService.findAllResource();

		return new ResponseEntity<>(schedulerData, HttpStatus.OK);
	}

	@PostMapping(value = "/appointment/create")
	public ResponseEntity<?> createAppointment(@RequestBody @Valid SchedulerMappingData appoitment) {
		LOGGER.info("Try to create new appointment: {}", appoitment.toString());
		try {
			appoitment = schedulerService.createScheduler(appoitment);
		} catch (MessagingException | IllegalStateException e) {
			LOGGER.error("Fail to save new Scheduler");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("errorMessage", e.getMessage()));
		}

		LOGGER.info("Succesfully create new appointment: {}", appoitment.getId());
		return new ResponseEntity<>(appoitment, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/appointment/delete/{appointmentId}/{checkCode}")
	public ResponseEntity<?> deleteAppointment(@PathVariable("appointmentId") String appoitmentId,
			@PathVariable("checkCode") final String checkCode) {
		LOGGER.info("Fetching & Deleting Appoitment with id: {}", appoitmentId);
		try {
			schedulerService.deleteScheduler(appoitmentId, checkCode);
		} catch (IllegalStateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return new ResponseEntity<>(Collections.singletonMap("id", appoitmentId), HttpStatus.OK);
	}

}
