package com.onlineSchool.AccountSubsystem;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private AccountRepository accountRepository;

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
	
	@RequestMapping(value = "/valid")
	public ModelAndView validRegister(@ModelAttribute("account") Account account, 
			                          @RequestParam("type") String type,
			                          @RequestParam("Academic Mail") String academicMail, 
			                          ModelAndView mav) {
		if (type.equals("student")) {
			account = new Student(account);
		} else {
			account = new Teacher(account, academicMail);
		}

		if (accountRepository.exists(account.getEmail())) {
			mav.addObject("exist", "This Email already exists please try again !");
			mav.setViewName(register);
		} else {
			accountRepository.save(account);
			mav.setViewName(login);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/login")
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName(login);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/valid2")
	public ModelAndView validLogin(@RequestParam("email") String email, 
								   @RequestParam("password") String password,
			                       ModelAndView mav) {

		Account account;
		if (accountRepository.exists(email)) {
			account = accountRepository.findOne(email);
		} else {
			mav.setViewName(login);
			mav.addObject("wrongpassword", "The Email you entered doesn't exist");
			return mav;
		}

		if (account.getPassword().equals(password)) {
			mav.setViewName(home);
		} else {
			mav.setViewName(login);
			mav.addObject("wrongpassword", "The password you entered doesn't match");
		}

		return mav;
	}
	
}
