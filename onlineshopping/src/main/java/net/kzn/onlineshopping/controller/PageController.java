package net.kzn.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "Home");
		view.addObject("userClickHome", "true");
		return view;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "About Us");
		view.addObject("userClickAbout", "true");
		return view;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "Contact Us");
		view.addObject("userClickContact", "true");
		return view;
	}
	
	@RequestMapping(value = "/allproducts")
	public ModelAndView allproducts() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "All Products");
		view.addObject("userClickAllProducts", "true");
		return view;
	}
}
