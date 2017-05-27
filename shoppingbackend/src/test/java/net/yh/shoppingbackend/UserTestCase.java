package net.yh.shoppingbackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.yh.shoppingbackend.dao.UserDAO;
import net.yh.shoppingbackend.dto.Address;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userdao;
	private User user = null;
	private Cart cart = null;
	private Address address = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.yh.shoppingbackend");
		context.refresh();
		
		userdao = (UserDAO) context.getBean("userDAO");
	}

	/*
	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("francois");
		user.setLastName("henzlick");
		user.setEmail("fh@gmx.com");
		user.setContactNumber("0665457898");
		user.setRole("USER");
		user.setPassword("123456");
		
		assertEquals("failed to add user!", true, userdao.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("30 rue de lens");
		address.setAddressLineTwo("appartment 222");
		address.setCity("lens");
		address.setState("hauts de france");
		address.setCountry("France");
		address.setPostalCode("62000");
		address.setBilling(true);
		//link the user with the address using the user id
		address.setUserId(user.getId());

		assertEquals("failed to add user address!", true, userdao.addAddress(address));
		
		if(user.getRole().equals("USER")){
			//create a cart for this user
			cart = new Cart();
			cart.setUser(user);
			
			//add the cart

			assertEquals("failed to add cart!", true, userdao.addCart(cart));
			
			
			//add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("30 rue de lens");
			address.setAddressLineTwo("appartment 222");
			address.setCity("lens");
			address.setState("hauts de france");
			address.setCountry("France");
			address.setPostalCode("62000");
			address.setShipping(true);
			//link it with user
			address.setUserId(user.getId());
			
			//add the shipping address
			assertEquals("failed to add shipping address!", true, userdao.addAddress(address));
			
			
		}
		
	}

	
*/
	
/*	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("youssef2");
		user.setLastName("harkati2");
		user.setEmail("yh2@gmx.com");
		user.setContactNumber("0665457898");
		user.setRole("USER");
		user.setPassword("123456");
		
	
		if(user.getRole().equals("USER")){
			//create a cart for this user
			cart = new Cart();
			cart.setUser(user);
			//attach cart with the user
			user.setCart(cart);
		
		}
		
		assertEquals("failed to add user!", true, userdao.addUser(user));
		
		
	}*/
	
	
	@Test
	public void testUpdateCart(){
		//fetch user by email
		user = userdao.getByEmail("yh2@gmx.com");
		//get the cart of the user
		cart = user.getCart();
		
		cart.setGrandTotal(5555);
		cart.setCartLines(2);
		assertEquals("failed to update the cart", true, userdao.updateCart(cart));
	}
	
}
