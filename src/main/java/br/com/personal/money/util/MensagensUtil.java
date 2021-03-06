package br.com.personal.money.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public final class MensagensUtil {
	
	private static MessageSource staticMessageSource;
	private static final Locale locale = LocaleContextHolder.getLocale();
	
	@Autowired
	private MessageSource messageSource;
	
	@PostConstruct
	public void init() {
		staticMessageSource = messageSource;
	}
	
	public static String getMessage(String key) {
		return staticMessageSource.getMessage(key, null, locale);
	}

	public static String getMessage(FieldError error) {
		return staticMessageSource.getMessage(error, locale);
	}
}
