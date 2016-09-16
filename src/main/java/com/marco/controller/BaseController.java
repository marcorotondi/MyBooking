/**
 *
 */
package com.marco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marco
 *
 */
@Controller
public class BaseController {

	@RequestMapping(name = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(name = "/doLogin", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "password", required = true) String password) {


		return null;
	}

}
