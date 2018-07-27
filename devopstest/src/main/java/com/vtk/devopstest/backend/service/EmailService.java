/**
 *
 */
package com.vtk.devopstest.backend.service;

import org.springframework.mail.SimpleMailMessage;

import com.vtk.devopstest.web.domain.frontend.FeedbackPojo;

/**
 * @author VK
 *
 */
public interface EmailService {

	/**
	 * Send an email the the content in the Feedback Pojo
	 * 
	 * @param feedbackPojo
	 */

	public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

	/**
	 * Send an email with the content in the Simple Mail Message Object
	 * 
	 * @param meaage
	 */
	public void sendGenericMapMessage(SimpleMailMessage message);

}
