package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("UserDAO");
	}

	
	/*  @Test public void testAdd(){
	 
	  user = new User();
	   user.setFirstName("Chandrakant");
	  user.setLastName("Kshirsagar"); user.setEmail("kshirsagarc07@gmail.com");
	  user.setContactNumber("8108561288"); user.setRole("USER");
	 user.setPassword("12345");
	 
	 
	  assertEquals("Failed to add user!",true,UserDAO.addUser(user));
	  
	 
	  address= new Address();
	  address.setAddressLingOne("Room no 3,Pramukh complex,");
	  address.setAddressLingTwo("Veera desai road,Andheri west");
	  address.setCity("Mumbai"); address.setState("maharashtra");
	   address.setCountry("India"); address.setPostalCode("400053");
	   address.setBilling(true);
	   
	   address.setUserId(user.getId());
	   
	   assertEquals("Failed to add Address!",true,UserDAO.addAddress(address));
	   
	   if(user.getRole().equals("USER")){
	   
	   cart=new Cart(); cart.setUser(user);
	   
	   assertEquals("Failed to add Cart!",true,UserDAO.addCart(cart));
	   
	   address= new Address();
	   address.setAddressLingOne("Room no 3,Pramukh complex,");
	   address.setAddressLingTwo("Veera desai road,Andheri west");
	   address.setCity("Mumbai"); address.setState("maharashtra");
	   address.setCountry("India"); address.setPostalCode("400053");
	   address.setShipping(true);
	   
	   address.setUserId(user.getId());
	   
	   assertEquals("Failed to Shipping Address!",true,UserDAO.addAddress(address));
	   
	    }
	   
	   
	   
	   }*/
	 

	/*@Test
	public void testAdd() {

		user = new User();
		user.setFirstName("Chandrakant");
		user.setLastName("Kshirsagar");
		user.setEmail("kshirsagarc07@gmail.com");
		user.setContactNumber("8108561288");
		user.setRole("USER");
		user.setPassword("12345");

		if (user.getRole().equals("USER")) {

			cart = new Cart();
			cart.setUser(user);
			
			user.setCart(cart);

		}

		assertEquals("Failed to add user!", true, UserDAO.addUser(user));

	}*/
	
	
	/*@Test
	public void testUpdateCart(){
		
		user =UserDAO.getbyEmail("kshirsagarc07@gmail.com");
		
		cart=user.getCart();
		
		cart.setGrandTotal(55550.00);
		cart.setCartLines(2);
		
		
		assertEquals("Failed to add Cart!", true, UserDAO.updateCart(cart));
	}
	*/
	
	/*@Test
	public void testAddress(){
		
		 user = new User();
		   user.setFirstName("Chandrakant");
		  user.setLastName("Kshirsagar"); user.setEmail("kshirsagarc07@gmail.com");
		  user.setContactNumber("8108561288"); user.setRole("USER");
		 user.setPassword("12345");
		 
		 
		  assertEquals("Failed to add user!",true,UserDAO.addUser(user));
		  
		  
		  address= new Address();
		  address.setAddressLingOne("Room no 3,Pramukh complex,");
		  address.setAddressLingTwo("Veera desai road,Andheri west");
		  address.setCity("Mumbai"); address.setState("maharashtra");
		   address.setCountry("India"); address.setPostalCode("400053");
		   address.setBilling(true);
		   
		   address.setUser(user);
		   
		   assertEquals("Failed to Billing Address!",true,UserDAO.addAddress(address));
		   
		   
		   address= new Address();
		   address.setAddressLingOne("Room no 3,Pramukh complex,");
		   address.setAddressLingTwo("Veera desai road,Andheri west");
		   address.setCity("Mumbai"); address.setState("maharashtra");
		   address.setCountry("India"); address.setPostalCode("400053");
		   address.setShipping(true);
		   
		   address.setUser(user);
		   
		   assertEquals("Failed to Shipping Address!",true,UserDAO.addAddress(address));
		   
		   
		 
		   
	}*/
	
	
	/*@Test
	public void testAddress(){
		
		User  user=UserDAO.getbyEmail("kshirsagarc07@gmail.com");
		
		  address= new Address();
		   address.setAddressLingOne("Room no 31,Shyam nagar,");
		   address.setAddressLingTwo("Jeevan nagar,Andheri west");
		   address.setCity("Mumbai"); address.setState("maharashtra");
		   address.setCountry("India"); address.setPostalCode("400059");
		   address.setShipping(true);
		   
		   address.setUser(user);
		   
		   assertEquals("Failed to Shipping Address!",true,UserDAO.addAddress(address));
		
	}
	*/
	
	
	@Test
	public void testGetAddresses(){
		User  user=userDAO.getbyEmail("kshirsagarc07@gmail.com");
		
		 assertEquals("Failed to get List shipping  Address!",2,userDAO.getListShippingAddress(user).size());
			
		 assertEquals("Failed to Billing Address!","Mumbai",userDAO.getBillingAddress(user).getCity());
	}
	
	
	
	
	
	
	
	

}
