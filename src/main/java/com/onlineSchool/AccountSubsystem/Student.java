package com.onlineSchool.AccountSubsystem;

public class Student extends Account {
	public Student(String username,String email,String password,String birthday,String gender){
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	public Student(){
		
	}
}
