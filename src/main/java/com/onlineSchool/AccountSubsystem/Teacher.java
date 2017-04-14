package com.onlineSchool.AccountSubsystem;

public class Teacher extends Account {
	private String 	academicMail;
	
	public Teacher() {
		
	}
	
	public Teacher(String username,String email,String password,String birthday,String gender,String AcademicMail) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.academicMail = AcademicMail;
	}
	
	

	public String getAcademicMail() {
		return academicMail;
	}

	public void setAcademicMail(String academicMail) {
		this.academicMail = academicMail;
	}
	
	
	
	
	
}
