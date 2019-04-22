package net.kzn.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.exception.ProductNotFoundException;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
public class PageController {

	public static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "Home");

		logger.info("Inside the pagecontroller index method - INFO");
		logger.debug("Inside the pagecontroller index method - DEBUG");

		view.addObject("userClickHome", "true");
		view.addObject("categories", categoryDAO.list());
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

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {

		ModelAndView view = new ModelAndView("page");

		Category category = null;
		category = categoryDAO.get(id);
		view.addObject("title", category.getName());
		view.addObject("userClickCategoryProducts", "true");
		view.addObject("category", category);
		view.addObject("categories", categoryDAO.list());

		return view;
	}

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "All Products");
		view.addObject("userClickAllProducts", "true");
		view.addObject("categories", categoryDAO.list());
		return view;
	}

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Product product = productDAO.get(id);

		if (product == null)
			throw new ProductNotFoundException();

		product.setViews(product.getViews() + 1);
		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProducts", "true");
		return mv;

	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {

		ModelAndView view = new ModelAndView("login");

		if (error != null) {
			view.addObject("message", "Invalid Username and password");
		}

		if (logout != null) {
			view.addObject("logout", "User has Successfully logged out!");
		}
		view.addObject("title", "Login");
		return view;
	}

	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {

		ModelAndView view = new ModelAndView("error");
		view.addObject("title", "403 Access _denied");
		view.addObject("errorTitle", "Aha! Caught you!");
		view.addObject("errorDescription", "You don't have authority to access this page!");
		return view;
	}

	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";

	}
}
