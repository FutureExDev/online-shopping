package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	HttpSession session;

	private Cart getCart() {

		return ((UserModel) session.getAttribute("userModel")).getCart();

	}

	public List<CartLine> getCartLines() {

		return cartLineDAO.list(this.getCart().getId());
	}

	public String manageCartLine(int cartLineId, int count) {

		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) {

			return "result=error";

		} else {
			Product product = cartLine.getProduct();

			double oldToatal = cartLine.getTotal();

			if (product.getQuantity() < count) {
				return "result=unavailable";
			}
			
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrize());
			cartLine.setTotal(product.getUnitPrize() *count);
			 
			cartLineDAO.update(cartLine);
			
			Cart cart=this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() - oldToatal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			return "result=updated";
		}

	}

	public String deleteCartLine(int cartLineId) {
		
		CartLine cartLine=cartLineDAO.get(cartLineId);
		
		if(cartLine==null){
			
			return "result=error";
		}
		else{
		
			Cart cart=this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal()- cartLine.getTotal());
			cart.setCartLines(cart.getCartLines()-1);
			
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartLine);
			
		return "result=deleted";
		}
	}

	public String addCartLine(int productId) {
		
		String response=null;
		
		Cart cart=this.getCart();
		
		CartLine cartLine =cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine==null){
			
			cartLine= new CartLine();
			
			Product product=productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrize());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrize());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines()+1);
			
			cart.setGrandTotal(cart.getGrandTotal()+ cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			response= "result=added";
			
		}
		else{
			
			if(cartLine.getProductCount()<3){
				
				response=this.manageCartLine(cartLine.getId(),cartLine.getProductCount()+1);
			}
			else{
				response="result=maximum";
			}
		}
		
		return response;
	}

}
