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

	public static final String INDEX_VIEW_NAME = "index";

	@RequestMapping("/")
	public String home() {
		return INDEX_VIEW_NAME;
	}

}
