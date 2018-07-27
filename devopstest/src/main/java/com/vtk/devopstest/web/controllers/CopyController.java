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
public class CopyController {

	public static final String ABOUT_VIEW_NAME = "copy/about";

	@RequestMapping("/about")
	public String about() {
		return ABOUT_VIEW_NAME;
	}

}