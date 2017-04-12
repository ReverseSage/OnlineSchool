package com.onlineSchool.Accsys.Controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	
	private String MainPage = "main";
	private String Register = "register";
	private String Login = "login";
	private String Home = "";
	
	HashMap<String, Account> RegisteredUsers = new HashMap<String, Account>();
		Account account ;

	@RequestMapping("/")
	public ModelAndView mainPage(ModelAndView mav)
	{
		mav.setViewName(MainPage);
		return mav;
	}
	
	@RequestMapping("/register")
	public String Register(@RequestParam("username")String username,
						 @RequestParam("email")String email,
						 @RequestParam("password")String password,
						 @RequestParam("date")String birthday,
						 @RequestParam("Gender")String gender,
						 @RequestParam("Academic Mail")String AcademicMail){
		
		if(AcademicMail.length() == 0)
			account = new Student(username,email,password,birthday,gender);
		else
			account = new Teacher(username,email,password,birthday,gender,AcademicMail);
		
		String response = "" ;
		if(RegisteredUsers.containsKey(account.getEmail())){
			response = Register;
		}
		else{
			RegisteredUsers.put(account.getEmail(), account);
			response = Login;
		}
		return response ;
	}
	
	@RequestMapping("/login")
	public String Login(@RequestParam("email")String email,
					@RequestParam("password")String password){
	
	String response = "" ;
	if(RegisteredUsers.containsKey(email) && RegisteredUsers.get(email).equals(password))
			response =  Home;
	else response = Login;
	
	return response;
	}
}
