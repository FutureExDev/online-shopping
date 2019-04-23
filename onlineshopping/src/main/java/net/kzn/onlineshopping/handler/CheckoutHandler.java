package net.kzn.onlineshopping.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.kzn.onlineshopping.model.CheckoutModel;
import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.OrderDetail;
import net.kzn.shoppingbackend.dto.OrderItem;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;

@Component
public class CheckoutHandler {

	public static final Logger LOGGER = LoggerFactory.getLogger(CheckoutHandler.class);

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private HttpSession session;

	public CheckoutModel init(String name) throws Exception {

		User user = userDAO.getbyEmail(name);
		CheckoutModel checkoutModel = null;

		if (user != null) {
			checkoutModel = new CheckoutModel();
			checkoutModel.setUser(user);
			checkoutModel.setCart(user.getCart());

			double checkoutTotal = 0.0;

			List<CartLine> cartLines = cartLineDAO.listAvailable(user.getCart().getId());

			if (cartLines.size() == 0) {
				throw new Exception("There is no products available for checkout Now!");

			}

			for (CartLine cartLine : cartLines) {
				checkoutTotal += cartLine.getTotal();
			}

			checkoutModel.setCartLines(cartLines);
			checkoutModel.setCheckoutTotal(checkoutTotal);

		}

		return checkoutModel;
	}

	public List<Address> getShippingAddress(CheckoutModel model) {

		List<Address> addresses = userDAO.getListShippingAddress(model.getUser());

		if (addresses.size() == 0) {
			addresses = new ArrayList<>();
		}
		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser()));

		return addresses;

	}

	public String saveAddressSelection(CheckoutModel model, int shippingId) {

		String transitionValue = "success";

		Address shipping = userDAO.getAddress(shippingId);

		model.setShipping(shipping);
		return transitionValue;

	}

	public String saveAddress(CheckoutModel checkoutModel, Address shipping) {

		String transitionValue = "success";

		shipping.setUser(checkoutModel.getUser());
		shipping.setShipping(true);
		userDAO.addAddress(shipping);

		checkoutModel.setShipping(shipping);

		return transitionValue;
	}

	public String saveOrder(CheckoutModel checkoutModel) {

		String transitionValue = "success";

		OrderDetail orderDetail = new OrderDetail();

		orderDetail.setUser(checkoutModel.getUser());
		orderDetail.setShipping(checkoutModel.getShipping());

		Address billing = userDAO.getBillingAddress(checkoutModel.getUser());
		orderDetail.setBilling(billing);

		List<CartLine> cartLines = checkoutModel.getCartLines();

		OrderItem orderItem = null;

		double orderTotal = 0.0;

		int orderCount = 0;
		Product product = null;

		for (CartLine cartLine : cartLines) {

			orderItem = new OrderItem();
			orderItem.setBuyingPrice(cartLine.getBuyingPrice());
			orderItem.setProduct(cartLine.getProduct());
			orderItem.setProductCount(cartLine.getProductCount());
			orderItem.setTotal(cartLine.getTotal());

			orderItem.setOrderDetail(orderDetail);
			orderDetail.getOrderItems().add(orderItem);

			orderTotal += orderItem.getTotal();
			orderCount++;

			product = cartLine.getProduct();
			product.setQuantity(product.getQuantity() - cartLine.getProductCount());
			product.setPurchases(product.getPurchases() + cartLine.getProductCount());
			productDAO.update(product);
			
			cartLineDAO.delete(cartLine);

		}

		orderDetail.setOrderTotal(orderTotal);
		orderDetail.setOrderCount(orderCount);
		orderDetail.setOrderDate(new Date());

		cartLineDAO.addOrderDetail(orderDetail);

		checkoutModel.setOrderDetail(orderDetail);

		Cart cart = checkoutModel.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - orderTotal);
		cart.setCartLines(cart.getCartLines() - orderCount);
		cartLineDAO.updateCart(cart);

		UserModel userModel = (UserModel) session.getAttribute("userModel");
		if (userModel != null) {

			userModel.setCart(cart);
		}

		return transitionValue;
	}

	public OrderDetail getOrderDetail(CheckoutModel checkoutModel) {
		return checkoutModel.getOrderDetail();
	}
	
}
