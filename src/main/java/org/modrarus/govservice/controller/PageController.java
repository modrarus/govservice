package org.modrarus.govservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер страниц
 */
@Controller
public class PageController {
	/**
	 * Страница со списком услуг
	 * @return Страница со списком сервисов
	 */
	@RequestMapping("/")
	public String index() {
		return "services.html";
	}
	
	/**
	 * Страница запроса услуги
	 * @return Страница с запросом услуги
	 */
	@RequestMapping("/request")
	public String requestService() {
		return "request.html";
	}
}