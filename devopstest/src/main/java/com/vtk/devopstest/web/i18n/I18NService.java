/**
 *
 */
package com.vtk.devopstest.web.i18n;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author VK
 *
 */
@Service
public class I18NService {

	private final static Logger LOG = LoggerFactory.getLogger(I18NService.class);

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String messageId) {
		LOG.info("********  messageId {}", messageId);
		Locale local = LocaleContextHolder.getLocale();
		return getMessage(messageId, local);
	}

	/**
	 * @param messageId
	 * @param local
	 * @return
	 */
	private String getMessage(String messageId, Locale local) {
		// TODO Auto-generated method stub
		return messageSource.getMessage(messageId, null, local);
	}
}
