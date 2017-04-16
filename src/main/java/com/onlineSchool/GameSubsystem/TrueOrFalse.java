package com.onlineSchool.GameSubsystem;

import javax.persistence.Entity;

@Entity
public class TrueOrFalse extends Question {
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
