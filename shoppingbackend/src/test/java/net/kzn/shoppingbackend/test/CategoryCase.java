package net.kzn.shoppingbackend.test;


import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

public class CategoryCase {

		private static AnnotationConfigApplicationContext context;
		
		private static CategoryDAO categoryDAO;
		
		private Category category;
		
		
		@BeforeClass
		public static void init(){
			
			context= new AnnotationConfigApplicationContext();
			context.scan("net.kzn.shoppingbackend");
			context.refresh();
			
			categoryDAO= (CategoryDAO) context.getBean("categoryDAO");
			
		}
		
		/*@Test
		public void testAddCategory(){
			
			category= new  Category();
			category.setId(2);
			category.setName("Mobile");
			category.setDescription("This is the some description for Mobile!");
			category.setImageURL("CAT_2.png");
			
			assertEquals("Successfully added a category inside the table!",true,categoryDAO.addCategory(category));
		}*/
		
		/*@Test
		public void testCategory(){
			
			category=categoryDAO.get(0);
			assertEquals("Successfully feacth data from table","Television",category.getName());
			
		}*/
		
		/*@Test
		public void testUpdateCategory(){
			
			category=categoryDAO.get(0);
			
			category.setName("TV");
			assertEquals("Successfully feacth data from table",true,categoryDAO.update(category));
			
		}*/
}
