package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

public class CartLineTestCase {

	public static AnnotationConfigApplicationContext context;

	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;

	private static Product product = null;
	private static User user = null;
	private static Cart cart = null;
	private static CartLine cartLine = null;


	
	
	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		
		productDAO=(ProductDAO) context.getBean("productDAO");
		userDAO=(UserDAO) context.getBean("userDAO");
		cartLineDAO=(CartLineDAO) context.getBean("cartLineDAO");
		
		
	}

	@Test
	public void testNewCartLine(){
		
		user=userDAO.getbyEmail("ckshirsagar280@gmail.com");
		
		cart=user.getCart();
		
		product=productDAO.get(2);
		
		cartLine=new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitPrize());
		
		cartLine.setProductCount(cartLine.getProductCount()+1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrize());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add cartline",true,cartLineDAO.add(cartLine));
		
		
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to add cartline",true,cartLineDAO.updateCart(cart));
		
	}
	
	
	
}
