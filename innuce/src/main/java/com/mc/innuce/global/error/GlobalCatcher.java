package com.mc.innuce.global.error;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalCatcher {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(FileNotFoundException.class)
	public String catcher2(Exception e,Model m) {
		m.addAttribute("ex",e);
		return "error";
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({NullPointerException.class,Exception.class})
	public String catcher(Exception e,Model m) {
		m.addAttribute("ex",e);
		return "nullError";
	}

//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ExceptionHandler(FileNotFoundException.class)
//	public String catcher2(Exception e,Model m) {
//		m.addAttribute("ex",e);
//		return "error";
//	}
//	
//	
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler({NullPointerException.class,Exception.class})
//	public String catcher(Exception e,Model m) {
////		m.addAttribute("ex",e);
//		return "error";
//	}

}
