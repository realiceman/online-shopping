package net.yh.shoppingbackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.yh.shoppingbackend.dao.CartLineDAO;
import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dao.UserDAO;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;
import net.yh.shoppingbackend.dto.Product;
import net.yh.shoppingbackend.dto.User;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartlinedao = null;
	private static ProductDAO productdao = null;
	private static UserDAO userdao = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartline = null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.yh.shoppingbackend");
		context.refresh();
		productdao = (ProductDAO) context.getBean("productDAO");
		userdao = (UserDAO) context.getBean("userDAO");
		cartlinedao = (CartLineDAO) context.getBean("cartlineDAO");
		
	}

	@Test
	public void testAddNewCartline() {
		//1 get the user
		user = userdao.getByEmail("youss.hark3@gmail.com");
		System.out.println("user id = "+user.getId());
		//2 fetch the cart
		cart = user.getCart();
		System.out.println("cart id = "+cart.getId());
		//3 get the product
		product = productdao.get(1);
		System.out.println("product id = "+product.getId());
		//4 create a new cartline
		cartline = new CartLine();
		cartline.setBuyingPrice(product.getUnitPrice());
		cartline.setProductCount(cartline.getProductCount()+1);
		cartline.setTotal(cartline.getProductCount()*product.getUnitPrice());
		System.out.println("cartline total price ="+ cartline.getTotal());
		cartline.setAvailable(true);
		cartline.setCartId(cart.getId());
		cartline.setProduct(product);
		assertEquals("failed to add cartline", true, cartlinedao.add(cartline));
		//5 update the cart
		cart.setGrandTotal(cart.getGrandTotal()+cartline.getTotal());
		System.out.println("cart grand total = "+ cart.getGrandTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("failed to update cart", true, cartlinedao.updateCart(cart));
		
	}

}
