package com.onlineSchool.GameSubsystem;

import java.util.List;

public class Game implements GameRepository {
	private List<Question> questions ;
	private String name;
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
