package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.service.CartService;

@Controller
@RequestMapping(value="/cart")
public class CartController {

	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result){
		
		ModelAndView view = new ModelAndView("page");
		
		if(result!=null){
		
		switch (result) {
		case "updated":
			view.addObject("message","Cartline has been successfully updated!");
			break;
		case "added":
			view.addObject("message","Cartline has been successfully Added!");
			break;
		case "maximum":
			view.addObject("message","Cartline has Reached to max count!");
			break;
		case "unavailable":
			view.addObject("message","Product quantity is not available!");
			break;
		case "error":
			view.addObject("message","Something went wrong!");
			break;
		}
		
		}
		view.addObject("title","User Cart");
		view.addObject("userClickShowCart",true);
		view.addObject("cartLines",cartService.getCartLines());	
		System.out.println(cartService.getCartLines());
		return view;
	}
	
	@RequestMapping(value="/{cartLineId}/update")
	public String updateCart(@PathVariable("cartLineId")int cartLineId,@RequestParam("count")int count){
		
		String response=cartService.manageCartLine(cartLineId,count);
		
		return "redirect:/cart/show?"+response;
		
		
	}
	
	@RequestMapping(value="/{cartLineId}/delete")
	public String deleteCart(@PathVariable("cartLineId")int cartLineId){
		
		String response=cartService.deleteCartLine(cartLineId);
		
		return "redirect:/cart/show?"+response;
		
		
	}
	
	@RequestMapping(value="/add/{productId}/product")
	public String addCart(@PathVariable("productId")int productId){
		
		String response=cartService.addCartLine(productId);
		
		return "redirect:/cart/show?"+response;
		
		
	}
}
