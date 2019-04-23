package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.OrderDetail;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {

		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartLine) {

		try {

			sessionFactory.getCurrentSession().persist(cartLine);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {

			sessionFactory.getCurrentSession().update(cartLine);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		try {

			sessionFactory.getCurrentSession().delete(cartLine);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId=:cartId";

		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class).setParameter("cartId", cartId)
				.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId=:cartId AND isAvailable=:available";

		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("available", true)
				.getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		
		try{
		String query = "FROM CartLine WHERE cartId=:cartId AND product.id= :productId";

		return sessionFactory.getCurrentSession().createQuery(query, CartLine.class)
				.setParameter("cartId", cartId)
				.setParameter("productId", productId)
				.getSingleResult();
		}
		
		catch (Exception e) {
		e.printStackTrace();
		return null;
		}
	}
	
	@Override
	public boolean updateCart(Cart cart) {
		try {

			sessionFactory.getCurrentSession().update(cart);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
	
		try {

			sessionFactory.getCurrentSession().persist(orderDetail);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
