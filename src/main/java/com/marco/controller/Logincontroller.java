/**
 *
 */
package com.marco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marco
 *
 */
@Controller
public class Logincontroller {

	@RequestMapping("/")
	public ModelAndView init() {
		return new ModelAndView("index.jsp");
	}

}
