/**
 *
 */
package com.vtk.devopstest.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author VK
 *
 */
@Controller
public class CopyController {

	private static final Logger LOG = LoggerFactory.getLogger(CopyController.class);

	public static final String ABOUT_VIEW_NAME = "copy/about";

	@RequestMapping("/about")
	public String about() {
		LOG.info("********** about: from 'about' and return 'copy/about'");
		return ABOUT_VIEW_NAME;
	}

}