package com.onlineSchool.CourseSubsystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseController {
	@Autowired
	CourseRepository courseRepository;
	
	@RequestMapping("/createCourse")
	String createCourse(@RequestParam("courseName") String courseName) {
		Course course = new Course();
		course.setCourseName(courseName);
		courseRepository.save(course);
		return "index";
	}
	
	
	
	
	

}
