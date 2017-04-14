package com.onlineSchool.AccountSubsystem;

public class StudentModel extends AccountModel {
	
	public boolean insert(Student student){
		return true;
	}
	
	public boolean exist(String username){
		return true;
	}
	
	public boolean exist(String username, String password){
		return true;
	}
	

}
