/**
 *
 */
package com.vtk.devopstest.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author VK
 *
 */
public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vtk.devopstest.backend.service.EmailService#sendGenericMapMessage(org.
	 * springframework.mail.SimpleMailMessage)
	 */
	@Override
	public void sendGenericMapMessage(SimpleMailMessage message) {
		LOG.info("********** sendGenericMapMessage: Simulating email service...");
		LOG.info(message.toString());
	}

}
