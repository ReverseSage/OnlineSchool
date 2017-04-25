package com.onlineSchool.GameSubsystem;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineSchool.CourseSubsystem.Course;
import com.onlineSchool.CourseSubsystem.CourseRepository;

@Controller
public class GameController {
	
	@Autowired
	GameRepository gameRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	Game game = new Game();
	private boolean step1 = false , step2 = false;
	
	
	@RequestMapping("/intialize")
	ModelAndView initializeGame(ModelAndView mav){
		List<Course> courses = courseRepository.findAll();
		mav.setViewName("initializeGame");
		mav.addObject("courses",courses);
		step1 = true;
		return mav;
	}
	
	@RequestMapping("/addQuestions")
	ModelAndView addQuestions(@RequestParam("courseName")String courseName,
								@RequestParam("gameName")String gameName,
								@RequestParam("Type")String type,
								@RequestParam("size")int size,
										ModelAndView mav) {			
		
		if(!step1){
			mav.setViewName("intializeGame");
			return mav;
		}
		step1 = false;
		
		if(gameRepository.exists(gameName)){
			mav.addObject("error","Game with the same name already exists please try again!");
			mav.setViewName("initializeGame");
			return mav;
		}
		
		game.setCourse(courseRepository.findOne(courseName));
		game.setGameName(gameName);
		
		if(type.equals("T/F")){
			List<TrueOrFalse> QUESTIONS = new ArrayList<TrueOrFalse>(size);
			mav.setViewName("TFView");
			mav.addObject("questions",QUESTIONS);
			step2 = true;
			return mav;
		}
		else if(type.equals("MCQ")){
			List<MultipleChoice> QUESTIONS = new ArrayList<MultipleChoice>(size);
			mav.setViewName("MCQ");
			mav.addObject("questions",QUESTIONS);
			step2 = true;
			return mav;
		}    
		else{
			//RunCode RUNCODE = new RunCode();
			mav.setViewName("RUNCode");
			mav.addObject("questions",null);
			step2 = true;
			return mav;
		}
		
	}
	
	@RequestMapping("/createGame")
	ModelAndView createGame(@ModelAttribute("questions")ArrayList<Question> questions,ModelAndView mav){
		
		if(!step2){
			mav.setViewName("intializeGame");
			return mav;
		}
		step2 = false;
		
		game.setQuestions(questions);
		gameRepository.save(game);
		
		
		return mav;
	}
	
		
	
	
	
	@RequestMapping("/playGame")
	ModelAndView playGame(@RequestParam("game")Game game,ModelAndView mav){
		
		return mav;
	}
	

}
