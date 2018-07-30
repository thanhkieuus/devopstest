/**
 *
 */
package com.vtk.devopstest.web.controllers;

import java.awt.Color;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vtk.devopstest.backend.persistence.domain.backend.PasswordResetToken;
import com.vtk.devopstest.backend.persistence.domain.backend.User;
import com.vtk.devopstest.backend.persistence.repositories.PasswordResetTokenRepository;
import com.vtk.devopstest.backend.service.EmailService;
import com.vtk.devopstest.backend.service.I18NService;
import com.vtk.devopstest.backend.service.PasswordResetTokenService;
import com.vtk.devopstest.utils.UserUtils;

/**
 * @author VK
 *
 */
@Controller
public class ForgotMyPasswordController {

	private static final Logger LOG = LoggerFactory.getLogger(ForgotMyPasswordController.class);

	public static final String EMAIL_ADDRESS_VIEW_NAME = "forgotpassword/emailForm";

	public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotmypassword";
	
	public static final String MAIL_SENT_KEY = "mailSent";

	public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword";
	
	public static final String EMAIL_MESSAGE_TEXT_PROPERTY_NAME = "forgotmypassword.email.text";

	@Autowired
	private I18NService i18NService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Value("${webmaster.email}")
    private String webMasterEmail;
	
	
	@RequestMapping(value=FORGOT_PASSWORD_URL_MAPPING, method=RequestMethod.GET)
	public String forgotPasswordGet() {
		
		LOG.info("********** forgotPasswordGet: enter");
		return EMAIL_ADDRESS_VIEW_NAME;
	}

	@RequestMapping(value=FORGOT_PASSWORD_URL_MAPPING, method=RequestMethod.POST)
	public String forgotPasswordPost(HttpServletRequest request,
									 @RequestParam("email") String email,
									 ModelMap model) {
		
		LOG.info("********** forgotPasswordPost: enter");
		PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);
		if (null == passwordResetToken) {
			LOG.warn("Could't find password reset tokem for email {}", email);
		}
		else {
			
			User user = passwordResetToken.getUser();
			String token = passwordResetToken.getToken();
			
			String resetPasswordUrl = UserUtils.createPasswordResetUrl(request, user.getId(), token);
			
			LOG.info("Reset Password URL {}", resetPasswordUrl);
			
			String emailText = i18NService.getMessage(EMAIL_MESSAGE_TEXT_PROPERTY_NAME, request.getLocale());
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("[DevopsTest]: How to reset your password");
			mailMessage.setText(emailText + "\r\n" + resetPasswordUrl);
			mailMessage.setFrom(webMasterEmail);
			
			emailService.sendGenericMapMessage(mailMessage);
		}

		model.addAttribute(MAIL_SENT_KEY, "true");
		
		return EMAIL_ADDRESS_VIEW_NAME;
	}
		
}
