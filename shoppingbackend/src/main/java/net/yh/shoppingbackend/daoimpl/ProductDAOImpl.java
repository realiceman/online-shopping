package net.yh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.yh.shoppingbackend.dao.ProductDAO;
import net.yh.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean add(Product product) {
		try {
		      sessionFactory
				.getCurrentSession()
					    .persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product get(int productId) {
		try {
			return sessionFactory
					 .getCurrentSession()
					    .get(Product.class,Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Product product) {
		try {
		      sessionFactory
				.getCurrentSession()
					    .update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		try {
			if(product.isActive()==true){
              return false;
			}
			 sessionFactory.getCurrentSession().delete(product);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Product> list() {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("FROM Product").list();
	}

	@Override
	public List<Product> listAllActiveProds() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts).setParameter("active",true).list() ;
	}

	@Override
	public List<Product> listAllActiveProdsByCat(int categoryId) {
	String selectActiveProductsByCat = "FROM Product WHERE active = :active AND category_id = :categoryId";
		
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductsByCat)
				    .setParameter("active",true)
				        .setParameter("category_id",categoryId).list() ;
	
	}

	@Override
	public List<Product> getLatestActiveProds(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product WHERE active = :active ORDER BY id")
				    .setParameter("active",true).setFirstResult(0)
				    .setMaxResults(count)
				       .list() ;
	}

}
