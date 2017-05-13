package com.onlineSchool.GameSubsystem;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.onlineSchool.AccountSubsystem.Account;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String text;
	private String date;
	private String time;
	@ManyToOne
	@JoinColumn(name = "email")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "gameName")
	private Game game;
	
	Comment() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = dateFormat.format(new Date()).toString();
		
		dateFormat = new SimpleDateFormat("HH:mm");
		time = dateFormat.format(new Date()).toString();
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

}
