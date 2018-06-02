/**
 *
 */
package com.vtk.devopstest.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.vtk.devopstest.web.domain.frontend.FeedbackPojo;

/**
 * @author VK
 *
 */
public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.to.address}")
	private String defaultToAddress;
	
	/**
	 * Create SimpleMail Message from Feedback Pojo
	 * @param feedbackPojo
	 * @return
	 */
	protected SimpleMailMessage prepairSimpleMailMEssageFromFeedbackPojo(FeedbackPojo feedbackPojo) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(defaultToAddress);
		message.setFrom(feedbackPojo.getEmail());
		message.setSubject("[DevopsTest]: Feedback received from " + feedbackPojo.getFirstName() + " " + feedbackPojo.getLastName() + "!");
		message.setText(feedbackPojo.getFeedback());
		return message;
	}
	
	/* (non-Javadoc)
	 * @see com.vtk.devopstest.backend.service.EmailService#sendFeedbackEmail(com.vtk.devopstest.web.domain.frontend.FeedbackPojo)
	 */
	@Override
	public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
		sendGenericMapMessage(prepairSimpleMailMEssageFromFeedbackPojo(feedbackPojo));		
	}
	

}
