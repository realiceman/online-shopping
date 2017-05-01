package net.yh.shoppingbackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dto.Product;

public class ProductTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.yh.shoppingbackend");
		context.refresh();
		
		productDAO= (ProductDAO) context.getBean("productDAO");
		
	}

	@Test
	public void testCRUDProduct() {
		product = new Product();
		product.setName("oneProd2");
		product.setBrand("youssBrand2");
		product.setDescription("une description...");
		product.setUnitPrice(200);
		product.setActive(true);
		product.setCategory_id(3);
		product.setSupplierId(3);
		
		assertEquals("ok",true, productDAO.add(product));
	}

}
