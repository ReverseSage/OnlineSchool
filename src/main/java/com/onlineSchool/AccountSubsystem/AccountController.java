package com.onlineSchool.AccountSubsystem;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
	private String MainPage  = "main" ;
	private String Register = "register";
	private String Login = "login";
	private String Home = "StudentHome";
	
	HashMap<String, Account> RegisteredUsers = new HashMap<String, Account>();
		Account account ;

	@RequestMapping("/")
	public String mainPage()
	{
		return MainPage;
	}
	
	@RequestMapping(value = "/register")
	public String register(){
		return Register;
	}
	
	@RequestMapping(value  = "/valid")
	public String validRegister(@RequestParam("userName")String username,
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
			response = "redirect:/register";
		}
		else{
			RegisteredUsers.put(account.getEmail(), account);
			response = "redirect:/login";
		}
		return response ;
	}
	
	@RequestMapping(value = "/login")
	public String login(){
		return Login;
	}
	
	@RequestMapping( value = "/valid2" )
	public String validLogin(@RequestParam("email")String email,
					@RequestParam("password")String password){
	
	String response = "" ;
	if(RegisteredUsers.get(email).getPassword().equals(password))
			response =  Home;
	else response = Login;
	
	return response;
	}
	
	@RequestMapping("/StudentHome")
	public String StudentHome(){
		return Home;
	}
	
	
}