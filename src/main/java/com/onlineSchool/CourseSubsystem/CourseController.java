package com.onlineSchool.CourseSubsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


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
			return mav;
		}
		courseRepository.save(course);
		mav.setViewName(home);  // redirecting to the home requires sending account
		
		return mav;
	}
	
	
	
	
	

}
