package com.maven.junit.game.TddTennisGame;

import iInterface.IPlayer;


public class Player2 implements IPlayer{
	// Variables
	private String name;
	private int score;
	
	// Constructors
	public Player2(String name) {
		super();
		this.name = name;
		this.score = 0;
	}
	public Player2() {
		super();
	}
	
	// Getters
	public String getName() {
		return this.name;
	}
	public int getScore() {
		return this.score;
	}
	
	// Setters
	public void setScore(int _score) {
		this.score = _score;
	}	
}
