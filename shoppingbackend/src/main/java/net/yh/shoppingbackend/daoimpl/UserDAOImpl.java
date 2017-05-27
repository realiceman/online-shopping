package net.yh.shoppingbackend.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.yh.shoppingbackend.dao.UserDAO;
import net.yh.shoppingbackend.dto.Address;
import net.yh.shoppingbackend.dto.Cart;
import net.yh.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	public SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		try {
		      sessionFactory
				.getCurrentSession()
					    .persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addAddress(Address address) {
		try {
		      sessionFactory
				.getCurrentSession()
					    .persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
		      sessionFactory
				.getCurrentSession()
					    .update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		
		try {
			return (User) sessionFactory.getCurrentSession()
					.createQuery(selectQuery).setParameter("email", email)
					.uniqueResult();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

}
