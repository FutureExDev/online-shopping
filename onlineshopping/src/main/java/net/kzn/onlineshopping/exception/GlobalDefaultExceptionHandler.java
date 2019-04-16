package net.kzn.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerFoundException(){
		
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","The page is not Contructed!");
		mv.addObject("errorDescription","The page you are looking for is not available now!");
		mv.addObject("title","404 Error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView productNotFoundException(){
		
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("errorTitle","Product not Available!");
		mv.addObject("errorDescription","The product you are looking for is not available  right now!");
		mv.addObject("title","product Unavailable");
		
		return mv;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex){
		
		ModelAndView mv=new ModelAndView("error");
		
		StringWriter sw=new StringWriter();
		PrintWriter pw= new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		mv.addObject("errorTitle","Contact your Administrator!");
		mv.addObject("errorDescription",sw.toString());
		mv.addObject("title","Error");
		
		return mv;
	}
	
}
