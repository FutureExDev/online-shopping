package net.kzn.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.kzn.onlineshopping.model.RegisterModel;
import net.kzn.shoppingbackend.dao.userDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private userDAO userDAO;

	public RegisterModel init() {

		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {

		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {

		registerModel.setBilling(billing);
	}

	public String saveAll(RegisterModel model) {

		String transitionValue = "success";

		User user = model.getUser();

		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();

			cart.setUser(user);
			user.setCart(cart);
		}

		userDAO.addUser(user);

		Address address = model.getBilling();

		address.setUser(user);
		address.setBilling(true);

		userDAO.addAddress(address);

		return transitionValue;

	}
	
	public String validateUser(User user,MessageContext error){
		
		String transitonValue="success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			
			error.addMessage(new MessageBuilder().error().
					source("confirmPassword").
					defaultText("password not matched to the confirm password")
					.build());
			
			transitonValue="failure";
		}
		
		if(userDAO.getbyEmail(user.getEmail())!=null){
			
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email Address Already Exists!").build());
			transitonValue="failure";
		}
		
		
		return transitonValue;
	}
	
	
	
	
	
	
}
