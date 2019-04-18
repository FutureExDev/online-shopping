package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.util.FileUploadUtility;
import net.kzn.onlineshopping.validator.ProductValidator;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
@RequestMapping(value = "/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static Logger logger = LoggerFactory.getLogger(ManagementController.class);

	
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Product");

		Product nproduct = productDAO.get(id);
		mv.addObject("product", nproduct);
			
		return mv;
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Product");

		Product nproduct = new Product();
		nproduct.setSupplierId(1);
		nproduct.setActive(true);
		mv.addObject("product", nproduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully!");
			}
			else{
				mv.addObject("message", "Category Submitted Successfully!");
			}
		}

		return mv;
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String handleProdcutSubmission(@Valid @ModelAttribute("product") Product nproduct, BindingResult result,
			Model model, HttpServletRequest request) {

		
		if(nproduct.getId()==0){
			new ProductValidator().validate(nproduct, result);
			
		}
		else{
			if(!nproduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(nproduct, result);
			}
		}
		

		if (!nproduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, nproduct.getFile(), nproduct.getCode());
		}

		if (result.hasErrors()) {

			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Product");
			model.addAttribute("message", "Validation failed for product submission!");
			return "page";
		}

		nproduct.setActive(true);
		nproduct.setSupplierId(1);
		logger.info(nproduct.toString());

		if(nproduct.getId()==0)
		{
		productDAO.add(nproduct);
		}
		else{
			productDAO.update(nproduct);
		}
	
		return "redirect:/manage/product?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {

		Product product = productDAO.get(id);

		boolean isactive = product.isActive();

		product.setActive(!product.isActive());

		productDAO.update(product);

		return (isactive) ? "You have successfully deactivated the product with id " + product.getId()
				: "You have successfully activated the product with id " + product.getId();
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		
		return new Category();
	}
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute(value="category")Category category ){
		
		categoryDAO.addCategory(category);
		
		return "redirect:/manage/product?operation=category";
	}
	
	

}
