/**
 *
 */
package com.marco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marco.service.ResourceRepository;
import com.marco.type.ResourceType;

/**
 * @author Marco
 *
 */
@Controller
public class AdminController {

	@Autowired
	private ResourceRepository resourceRepo;

	@RequestMapping(value = "/admin/adminPanel.html", method = RequestMethod.GET)
	public ModelAndView adminIndex() {
		final ModelAndView resultView = new ModelAndView("admin/adminPanel");
		resultView.addObject("resources", resourceRepo.findAll());
		resultView.addObject("resourceTypes", ResourceType.values());
		return resultView;
	}

	@RequestMapping(value = "/admin/addResource", method = RequestMethod.POST)
	public ModelAndView addResource() {
		return null;
	}



}
