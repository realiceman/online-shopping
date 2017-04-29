package net.yh.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dto.Category;

@Repository("categorydao")
public class CategoryDaoImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<Category>();
	
	static{
		Category category = new Category();
		category.setId(1);
		category.setName("tv");
		category.setDescription("television");
		category.setImageURL("image.png");
		categories.add(category);
		
		category = new Category();
		category.setId(2);
		category.setName("tv2");
		category.setDescription("television2");
		category.setImageURL("image2.png");
		categories.add(category);
	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		Category resultat = null;
		for (Category category : categories) {
			if(category.getId()==id){
				resultat= category;
			}
		}
		return resultat;
		
	}

}
