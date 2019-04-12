package net.kzn.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;


@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static List<Category> categories=new ArrayList<>();
	
	
	/*static{
		
		Category category = new Category();
		
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is the some description for Television!");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		category=new Category();
			category.setId(2);
			category.setName("Mobile");
			category.setDescription("This is the some description for Mobile!");
			category.setImageURL("CAT_2.png");
		categories.add(category);
		
		category=new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is the some description for Laptop!");
		category.setImageURL("CAT_3.png");
		categories.add(category);
	}*/
	
	@Override
	public List<Category> list() {
		
		String selectActiveCategory="FROM Category WHERE active= :active";
		
		Query query=sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
		
		return query.getResultList();
	}

	@Override
	public Category get(int id) {
/*
			for(Category category:categories){
				
				if(category.getId()==id) return category;
			}
			
*/
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
		
	}

	@Override
	public boolean addCategory(Category category) {
		try{
			
		sessionFactory.getCurrentSession().persist(category);			
			return true;
		}
		catch(Exception e){
			
			return false;
		}
		
		
	}

	@Override
	public boolean update(Category category) {
		try{
			
			sessionFactory.getCurrentSession().update(category);			
				return true;
			}
			catch(Exception e){
				
				return false;
			}
			
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
