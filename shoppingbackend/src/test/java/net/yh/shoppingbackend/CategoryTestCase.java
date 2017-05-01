package net.yh.shoppingbackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("net.yh.shoppingbackend");
		context.refresh();
		
		categoryDAO= (CategoryDAO) context.getBean("categoryDAO");
		
	}
	
	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("game");
		category.setActive(true);
		category.setDescription("super game");
		category.setImageURL("game.png");
		assertEquals("Successfully added a category inside the table!", true, categoryDAO.add(category));
	}
	
//	@Test
//	public void testGetCategory(){
//		category = categoryDAO.get(1);
//		assertEquals("Successfully fetched category from the table!", 1, category.getId());
//	}
	
//	@Test
//	public void testUpdateCategory(){
//		category = categoryDAO.get(1);
//		category.setImageURL("realtv.png");
//		assertEquals("Successfully updated category from the table!", true, categoryDAO.update(category));
//	}
	
//	@Test(expected=java.lang.AssertionError.class )
//	public void testDeleteCategory(){
//		category = categoryDAO.get(3);  // category avec un "active" à true
//		assertEquals("Successfully deleted category from the table!", true, categoryDAO.delete(category));
//	}
	
//	@Test
//	public void testListCats(){
//		assertEquals("Successfully fetched list of categories!", 2, categoryDAO.listAllActiveCats().size());
//	}
	
	
	@Test
	public void testCRUDCategory(){
		/*
		 * create cats
		 * assert them
		 * update a cat
		 * assert
		 * assert size
		 * delete cat
		 * assert
		 */
		
	}

}
