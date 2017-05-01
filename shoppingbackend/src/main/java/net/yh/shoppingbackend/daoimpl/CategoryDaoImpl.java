package net.yh.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.yh.shoppingbackend.dao.CategoryDAO;
import net.yh.shoppingbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDaoImpl implements CategoryDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	private static List<Category> categories = new ArrayList<Category>();
	
//	static{
//		Category category = new Category();
//		category.setId(1);
//		category.setName("tv");
//		category.setDescription("television");
//		category.setImageURL("image.png");
//		categories.add(category);
//		
//		category = new Category();
//		category.setId(2);
//		category.setName("tv2");
//		category.setDescription("television2");
//		category.setImageURL("image2.png");
//		categories.add(category);
//	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		//return categories;
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active",true);
		return query.list();
	}
	
	
	public List<Category> listAllActiveCats() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active",true);
		return query.list();
	}
	

	@Override
	public Category get(int id) {
//		Category resultat = null;
//		for (Category category : categories) {
//			if(category.getId()==id){
//				resultat= category;
//			}
//		}
//		return resultat;
		return sessionFactory.getCurrentSession().get(Category.class,Integer.valueOf(id));
		
	}

	@Override
	public boolean add(Category category) {
		try {
			sessionFactory.getCurrentSession().persist(category);
           return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
           return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try {
			if(category.isActive()==true){
              return false;
			}
			 sessionFactory.getCurrentSession().delete(category);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
