package net.yh.shoppingbackend.dao;

import net.yh.shoppingbackend.dto.Address;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getByEmail(String email);
	boolean addAddress(Address address);
	boolean updateCart(Cart cart);

}
