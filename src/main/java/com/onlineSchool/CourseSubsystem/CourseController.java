package com.onlineSchool.CourseSubsystem;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineSchool.GameSubsystem.Game;
import com.onlineSchool.GameSubsystem.Question;

@PropertySource(value={"classpath:application.properties"})

@Controller
public class CourseController {
	private String home = "TeacherHome";
	
	@Autowired
	CourseRepository courseRepository;
	
	@RequestMapping("/createCourse")
	ModelAndView createCourse(ModelAndView mav) {
		mav.setViewName("createCourse");
		mav.addObject("course", new Course());
		return mav;
	}
	
	@RequestMapping("/validCourse")
	ModelAndView validCourse(@ModelAttribute("course") Course course, ModelAndView mav){
		if(courseRepository.exists(course.getCourseName())){
			mav.setViewName("createCourse");
			mav.addObject("error","Course name already exists. want to try another name?");
			
			return mav;
		}
		courseRepository.save(course);
		mav.setViewName(home);  
		List<Course> courses = courseRepository.findAll();
		mav.addObject("courses",courses);
		return mav;
	}
	@RequestMapping("/Games")
	ModelAndView showGames(@RequestParam("courseName")String courseName, ModelAndView mav)
	{
		mav.setViewName("CourseGames");
		courseName = courseName.substring(1, courseName.length());
		courseName = courseName.substring(0,courseName.length() -1 );
		List<Course> courses = courseRepository.findAll();
		List<Game> games = new ArrayList<Game>();
		
		for(int i = 0 ; i < courses.size(); i++){
			 String name = courses.get(i).getCourseName();
		  if((name.equals(courseName)))
		  {
			  games = courses.get(i).getGames();
			  break;
		  }
		}
		mav.addObject("games" , games);
		return mav;
	}	
}
	
