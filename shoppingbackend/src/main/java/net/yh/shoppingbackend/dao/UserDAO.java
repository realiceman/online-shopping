package net.yh.shoppingbackend.dao;

import java.util.List;

import net.yh.shoppingbackend.dto.Address;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getByEmail(String email);
	boolean addAddress(Address address);
	
	//alternatives pour eviter les baisses de perfs web => id plutot qu'objet
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	boolean updateCart(Cart cart);

}
