package net.yh.shoppingbackend.dao;

import java.util.List;

import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;

public interface CartLineDAO {
	// common methods 
	public CartLine get(int id);
	public boolean add(CartLine cartline);
	public boolean update(CartLine cartline); 
	public boolean delete(CartLine cartline);
	public List<CartLine> list(int cartId);
	//business methods related to the cart lines
	public List<CartLine> listAvailable(int cartId);
	public CartLine getByCartAndProduct(int id, int productId);
	// update the cart
	boolean updateCart(Cart cart);
}
