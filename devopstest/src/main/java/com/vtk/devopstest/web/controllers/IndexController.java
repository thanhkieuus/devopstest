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
public class IndexController {

	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	public static final String INDEX_VIEW_NAME = "index";

	@RequestMapping("/")
	public String home() {
		LOG.info("********** home: from '/' and return 'index'");
		return INDEX_VIEW_NAME;
	}

}
