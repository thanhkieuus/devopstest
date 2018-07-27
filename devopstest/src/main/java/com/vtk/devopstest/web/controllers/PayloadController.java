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
public class PayloadController {

	private static final String PAYLAAD_VIEW_NAME = "payload/payload";

	@RequestMapping("/payload")
	public String payload() {
		return PAYLAAD_VIEW_NAME;
	}

}
