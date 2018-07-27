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
public class LoginController {

	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	public static final String LOGIN_VIEW_NAME = "user/login";

	@RequestMapping("/login")
	public String login() {

		LOG.info("************ Login...");
		return LOGIN_VIEW_NAME;

	}

}
