package org.modrarus.govservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Контроллер страниц
 */
@Controller
public class IndexController {
	/**
	 * Основная страница
	 * @return Основная страница
	 */
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
}