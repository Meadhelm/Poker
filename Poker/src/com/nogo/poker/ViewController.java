package com.nogo.poker;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Serves up web pages.
 */
@Controller
public class ViewController {

	/**
	 * Serves up the default page
	 *
	 * @return home.html
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String home() {
		return "home";
	}

	/**
	 * Serves requested web page and robustly handles variations of home page requests.
	 *
	 * @param page
	 *            requested web page
	 * @return {page}.html
	 */
	@RequestMapping(value = "/{page}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String page(@PathVariable(value = "page") String page) {
		if ("home".equals(page) | "home.html".equals(page)
				| "home.jsp".equals(page)) {
			return "home";
		}
		return page;
	}

}