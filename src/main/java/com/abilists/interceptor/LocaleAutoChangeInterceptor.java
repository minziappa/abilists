package com.abilists.interceptor;

import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * For refactoring
 * 
 * @author njoonk
 *
 */
public class LocaleAutoChangeInterceptor extends LocaleChangeInterceptor {
	String LANG = "en-US";
	private String paramName = DEFAULT_PARAM_NAME;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {

		String newLocale = request.getParameter(this.paramName);
		if (newLocale != null) {
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			if (localeResolver == null) {
				throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
			}
			localeResolver.setLocale(request, response, StringUtils.parseLocaleString(newLocale));
			request.getSession().setAttribute("userLocale", newLocale);
		}

		Object objValue = request.getSession().getAttribute("userLocale");
		if(objValue == null && newLocale == null) {
			String acceptLanguage = request.getHeader("Accept-Language");
			if (acceptLanguage == null) {
				acceptLanguage = LANG;
			}
			String languageSet = acceptLanguage.split("-")[0];
			// Set locale by your browser.
			Locale locale = Locale.forLanguageTag(languageSet);
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			localeResolver.setLocale(request, response, locale);
			// Set locale language in menu.
			request.setAttribute("lang", locale.toLanguageTag());
		}

		return true;
	}

}
