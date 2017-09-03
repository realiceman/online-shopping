package net.yh.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.yh.shoppingbackend.dao.CartLineDAO;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.CartLine;

@Repository("cartlineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {
	
	@Autowired
	private SessionFactory sessionfactory;

	@Override
	public CartLine get(int id) {
		// TODO Auto-generated method stub
		return sessionfactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartline) {
		try {
			sessionfactory.getCurrentSession().persist(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartline) {
		try {
			sessionfactory.getCurrentSession().update(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartline) {
		try {
			sessionfactory.getCurrentSession().delete(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionfactory.getCurrentSession()
		                .createQuery(query)
		                  .setParameter("cartId", cartId)
		                    .list();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available= :available";
		return sessionfactory.getCurrentSession()
		                .createQuery(query)
		                  .setParameter("cartId", cartId)
		                   .setParameter("available", true)
		                    .list();
	}

	@Override
	public CartLine getByCartAndProduct(int id, int productId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id= :productId";
		try {
			Object obj =  (Object) sessionfactory.getCurrentSession()
					
			                .createQuery(query)
			                  .setParameter("cartId", id)
			                   .setParameter("productId", productId)
			                    .getFirstResult();
			CartLine cartline = (CartLine) obj;
			return cartline;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionfactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
