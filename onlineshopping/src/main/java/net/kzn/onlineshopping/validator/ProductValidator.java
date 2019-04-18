package net.kzn.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.kzn.shoppingbackend.dto.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clzz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clzz);
	}

	@Override
	public void validate(Object target, Errors error) {
		// TODO Auto-generated method stub

		Product product = (Product) target;

		if (product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			
			error.rejectValue("file",null,"Please select file to upload!");
			return;
		}
		
		if(!( product.getFile().getContentType().equals("image/jpeg") ||
				product.getFile().getContentType().equals("image/png") ||
				product.getFile().getContentType().equals("image/gif")
				)){
			error.rejectValue("file",null,"Please use only image file to upload!");
			return;
		}
	}

}
