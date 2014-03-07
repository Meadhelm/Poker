package com.nogo.poker;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		return "home";
	}

	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String page(Locale locale, Model model,
			@PathVariable(value = "page") String page) {
		if ("home".equals(page) | "home.html".equals(page)
				| "home.jsp".equals(page)) {
			return "home";
		}
		return page;
	}

}