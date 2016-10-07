/**
 *
 */
package com.marco.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

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

	@RequestMapping(value = "/admin/adminPanel.html", method = RequestMethod.GET)
	public ModelAndView adminIndex() {
		final ModelAndView resultView = new ModelAndView("admin/adminPanel");
		resultView.addObject("resources", resourceRepo.findAll());
		resultView.addObject("resourceTypes", ResourceType.values());
		return resultView;
	}

	@RequestMapping(value = "/admin/api/crudResource", method = RequestMethod.POST)
	public ResponseEntity<Void> crudResource(@RequestBody Resource resource, UriComponentsBuilder ucBuilder) {
		LOGGER.info("Creating / Update Resource {}", resource);		
		
		resourceRepo.save(resource);
	    return new ResponseEntity<Void>(OK);
	}
	
	@RequestMapping(value = "/admin/api/delete/resource/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Resource> deleteUser(@PathVariable("id") long id) {
       LOGGER.info("Fetching & Deleting Resource with id: {}", id);
  
       final Resource res = resourceRepo.findOne(id);
       if (null == res) {
    	   LOGGER.warn("Unable to delete. Resource with id {} not found", id);
    	   return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
       }
       
       resourceRepo.delete(id);
       return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
    }

	@RequestMapping(value = "/admin/api/resources.json", method = RequestMethod.GET)
	public ResponseEntity<List<Resource>> getAllResources() {
		return new ResponseEntity<List<Resource>>(resourceRepo.findAll(), HttpStatus.OK);
	}
}
