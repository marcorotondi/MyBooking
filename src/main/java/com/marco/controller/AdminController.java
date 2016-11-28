/**
 *
 */
package com.marco.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.marco.model.Resource;
import com.marco.service.ResourceRepository;
import com.marco.type.ResourceType;

/**
 * @author Marco
 *
 */
@RestController
public class AdminController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private ResourceRepository resourceRepo;

	@PostMapping(value = "/admin/api/crudResource")
	public ResponseEntity<Void> crudResource(@RequestBody @Valid Resource resource) {
		LOGGER.info("Try To Creating / Update Resource {}", resource);

		resourceRepo.save(resource);

		LOGGER.info("Successfully Creating / Update Resource {}", resource);
		return new ResponseEntity<>(OK);
	}

	@DeleteMapping(value = "/admin/api/delete/resource/{id}")
	public ResponseEntity<Resource> deleteUser(@PathVariable("id") long id) {
		LOGGER.info("Fetching & Deleting Resource with id: {}", id);

		final Resource res = resourceRepo.findOne(id);
		if (null == res) {
			LOGGER.warn("Unable to delete. Resource with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		resourceRepo.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/admin/api/resources.json")
	public ResponseEntity<List<Resource>> getAllResources() {
		final List<Resource> allResource = resourceRepo.findAll();
		return new ResponseEntity<>(allResource, HttpStatus.OK);
	}

	@GetMapping(value = "/admin/api/summary.json")
	public ResponseEntity<Map<String, Long>> getSummaryCounters() {
		final Map<String, Long> counterMap = new HashMap<>();

		counterMap.put("resource_count", resourceRepo.count());
		counterMap.put("resource_room", resourceRepo.countByType(ResourceType.ROOM));
		counterMap.put("resource_car", resourceRepo.countByType(ResourceType.CAR));
		counterMap.put("resource_obj", resourceRepo.countByType(ResourceType.OBJECT));

		return new ResponseEntity<>(counterMap, OK);
	}
}
