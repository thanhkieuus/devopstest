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
public class PayloadController {

	private static final Logger LOG = LoggerFactory.getLogger(PayloadController.class);

	private static final String PAYLAAD_VIEW_NAME = "payload/payload";

	@RequestMapping("/payload")
	public String payload() {
		LOG.info("********** payload: from 'payload' and return 'payload/payload'");
		return PAYLAAD_VIEW_NAME;
	}

}
