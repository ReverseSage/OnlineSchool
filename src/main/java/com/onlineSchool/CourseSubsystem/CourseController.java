package com.onlineSchool.CourseSubsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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
			mav.addObject("error","Course name already exists. want to try another name?");
			mav.setViewName("createCourse");
			return mav;
		}
		courseRepository.save(course);
		mav.setViewName(home);  
		List<Course> courses = courseRepository.findAll();
		mav.addObject("courses",courses);
		return mav;
	}
	
	@RequestMapping("/courseGames")
	ModelAndView courseGames(@RequestParam("courseName")String coursName ,ModelAndView mav){
		
		// send all games in one category
		return mav;
	}
	
	
	

}
