package com.onlineSchool.CourseSubsystem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.onlineSchool.GameSubsystem.Game;

@Entity
public class Course {
	@Id
	private String courseName;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private List<Game> games;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}

}
