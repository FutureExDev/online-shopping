package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	
	User getbyEmail(String email);

	Address getAddress(int addressId);
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> getListShippingAddress(User user);
	
	
	
}
