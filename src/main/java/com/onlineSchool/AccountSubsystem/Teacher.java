package com.onlineSchool.AccountSubsystem;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.onlineSchool.GameSubsystem.Game;

@Entity
public class Teacher extends Account {
	private String academicmail;
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<Game> game;

	public Teacher() {

	}

	public Teacher(Account account, String academicmail) {
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

	public List<Game> getGame() {
		return game;
	}

	public void setGame(List<Game> game) {
		this.game = game;
	}

}
