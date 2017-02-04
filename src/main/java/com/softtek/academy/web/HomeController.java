package com.softtek.academy.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtek.academy.domain.UserEntity;
import com.softtek.academy.services.UserService;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String adminHome() {
		return "adminHome";
	}
	
	// If user is admin or only registred, redirect to respective page 
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public String redirect() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final String role=auth.getAuthorities().toString();
		if(role.equals("[ROLE_ADMIN]")){
			return "redirect:/admin/home";
					
		}
		return "redirect:/";
	}
	
	//show the username 
	@RequestMapping(value = "/userName", method = RequestMethod.GET)
	public ResponseEntity<?>  logName() {
		 UserEntity user;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null){
			 user=userService.findOne(auth.getName());
			if(user==null ){
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
		}

		user=null;
		return  new ResponseEntity<UserEntity>(user, HttpStatus.OK);
		
	}
}