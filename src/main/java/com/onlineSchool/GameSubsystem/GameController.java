package com.onlineSchool.GameSubsystem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineSchool.CourseSubsystem.Course;

@Controller
public class GameController {
	@Autowired
	GameRepository gameRepository;
	
	
	@RequestMapping("/createTrueOrFlaseGame")
	String createTrueOrFalseGame(@RequestParam("courseName") String courseName,
								 @RequestParam("gameName") String gameName) {
		Course course = new Course();
		Game game = new Game();
		List <Question> questions = new ArrayList <Question> ();
		
		game.setGameName(gameName);
		
		TrueOrFalse question1 = new TrueOrFalse();
		question1.setHeader("5 + 3 = 4?");
		question1.setAnswer("False");
		question1.setGame(game);
		
		TrueOrFalse question2 = new TrueOrFalse();
		question2.setHeader("1 + 1 = 2?");
		question2.setAnswer("True");
		question2.setGame(game);
		
		questions.add(question1);
		questions.add(question2);
		
		game.setQuestions(questions);
		
		course.setCourseName(courseName);
		
		game.setCourse(course);
		
		gameRepository.save(game);
		
		return "index";
	}
	
	@RequestMapping("/playGame")
	String playGame(@RequestParam("gameName")String gameName) {
		
		Game game = gameRepository.findOne(gameName);
		if(game.getQuestions().get(0).getClass() == TrueOrFalse.class) {
			for (int i = 0; i < game.getQuestions().size(); i++) {
				System.out.println(((TrueOrFalse) game.getQuestions().get(i)).getHeader());
				System.out.println(((TrueOrFalse) game.getQuestions().get(i)).getAnswer());
			}
		}
		
		return "index";
	}

}
