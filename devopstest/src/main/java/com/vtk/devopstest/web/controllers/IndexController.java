/**
 *
 */
package com.vtk.devopstest.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author VK
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
