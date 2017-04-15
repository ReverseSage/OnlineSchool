package com.onlineSchool.AccountSubsystem;

public class Student extends Account {
	
	public Student(){
		
	}
	public Student(Account account){
		this.username = account.getUsername();
		this.email = account.getEmail();
		this.password = account.getPassword();
		this.birthday = account.getBirthday();
		this.gender = account.getGender();
	}
}
