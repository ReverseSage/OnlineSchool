package com.onlineSchool.AccountSubsystem;

public class Teacher extends Account {
	private String 	academicmail;
	
	public Teacher() {
		
	}
	
	public Teacher(Account account,String academicmail) {
		this.username = account.getUsername();
		this.email = account.getEmail();
		this.password = account.getPassword();
		this.birthday = account.getBirthday();
		this.gender = account.getGender();
		this.academicmail = academicmail;
	}
	
	

	public String getAcademicMail() {
		return academicmail;
	}

	public void setAcademicMail(String academicMail) {
		this.academicmail = academicMail;
	}
	
	
	
	
	
}
