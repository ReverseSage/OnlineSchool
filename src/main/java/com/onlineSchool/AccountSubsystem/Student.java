package com.onlineSchool.AccountSubsystem;

import javax.persistence.Entity;

@Entity
public class Student extends Account {

	public Student() {

	}

	public Student(Account account) {
		this.setUsername(account.getUsername());
		this.setEmail(account.getEmail());
		this.setPassword(account.getPassword());
		this.setBirthday(account.getBirthday());
		this.setGender(account.getGender());
	}
}