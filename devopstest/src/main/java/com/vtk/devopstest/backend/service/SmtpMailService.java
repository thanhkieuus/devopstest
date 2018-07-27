/**
 *
 */
package com.vtk.devopstest.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author VK
 *
 */
public class SmtpMailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);

	@Autowired
	private MailSender mailSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vtk.devopstest.backend.service.EmailService#sendGenericMapMessage(org.
	 * springframework.mail.SimpleMailMessage)
	 */
	@Override
	public void sendGenericMapMessage(SimpleMailMessage message) {
		LOG.debug("Seding mail for {}", message);
		mailSender.send(message);
		LOG.info("Email sent.");
	}

}
