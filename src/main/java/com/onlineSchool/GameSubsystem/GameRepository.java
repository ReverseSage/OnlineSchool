package com.onlineSchool.GameSubsystem;

import java.util.List;


public interface GameRepository {
	
	Game findOne(String name);
	Game save(Game game);
	List<Game> findAll();
	
	
}
