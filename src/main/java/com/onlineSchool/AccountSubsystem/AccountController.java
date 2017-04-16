package com.onlineSchool.AccountSubsystem;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	private String mainpage  = "main" ;
	private String register = "register";
	private String login = "login";
	private String home = "StudentHome";
	private String test = "index";
	private HashMap<String, Account> RegisteredUsers = new HashMap<String, Account>();
		Account account ;

	@RequestMapping("/")
	public String mainPage()
	{
		return mainpage;
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView register(ModelAndView mav){
		mav.setViewName(register);
		mav.addObject("account",new Account());
		return mav;
	}
	
	@RequestMapping(value  = "/valid")
	public ModelAndView validRegister(@ModelAttribute("account")Account account,
								@RequestParam("type")String type,
								@RequestParam("Academic Mail")String academicmail,
								ModelAndView mav){
		if(type.equals("student"))
			account = new Student(account);
		else
			account = new Teacher(account,academicmail);
		
		
		if(RegisteredUsers.containsKey(account.getEmail())){
			mav.addObject("exist","This Email already exists please try again !");
			mav.setViewName(register);
		}
		else{
			RegisteredUsers.put(account.getEmail(), account);
			mav.setViewName(login);
		}
		return mav ;
	}
	
	
	
	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView mav){
		mav.setViewName(login);
		return mav;
	}
	
	
	
	@RequestMapping( value = "/valid2" )
	public ModelAndView validLogin(@RequestParam("email")String email,
								   @RequestParam("password")String password,
									ModelAndView mav){
		
		if(!RegisteredUsers.containsKey(email)){
			mav.setViewName(login);
			mav.addObject("wrongpassword", "The Email you entered doesn't exist");
			return mav;
		}
		
	if(RegisteredUsers.get(email).getPassword().equals(password) )
			mav.setViewName(home); //send to home
	else{
		mav.setViewName(login);
		mav.addObject("wrongpassword", "The password you entered doesn't match");
	}
	
	return mav;
	}
	
	
	
	
}
