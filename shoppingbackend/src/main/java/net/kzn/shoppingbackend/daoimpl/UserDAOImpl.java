package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {

		try {

			sessionFactory.getCurrentSession().persist(user);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addAddress(Address address) {
		try {

			sessionFactory.getCurrentSession().persist(address);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	@Override
	public User getbyEmail(String email) {

		String selectQuery = "FROM User WHERE email=:email";

		try {

			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Address getBillingAddress(User user) {
		 try {
			String selectQuery="FROM Address WHERE user=:user AND billing=:billing";
			 
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class)
					.setParameter("user",user)
					.setParameter("billing",true)
					.getSingleResult();
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> getListShippingAddress(User user) {
		try {
			String selectQuery="FROM Address WHERE user=:user AND shipping=:shipping";
			 
			return sessionFactory.getCurrentSession().createQuery(selectQuery,Address.class)
					.setParameter("user",user)
					.setParameter("shipping",true)
					.getResultList();
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
