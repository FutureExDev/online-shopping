package net.kzn.onlineshopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.exception.ProductNotFoundException;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
public class PageController {

	public static final Logger logger=LoggerFactory.getLogger(PageController.class);
	
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
		view.addObject("categories",categoryDAO.list());
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
		
		Category category =null;
		category=categoryDAO.get(id);
		view.addObject("title", category.getName());
		view.addObject("userClickCategoryProducts", "true");
		view.addObject("category",category);
		view.addObject("categories",categoryDAO.list());
		
		return view;
	}
	
	
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() { 

		ModelAndView view = new ModelAndView("page");
		view.addObject("title", "All Products");
		view.addObject("userClickAllProducts", "true");
		view.addObject("categories",categoryDAO.list());
		return view;
	}
	
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv= new ModelAndView("page");
		
		Product product =productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProducts", "true");
		return mv;
		
		
	}
	
	
	
}
