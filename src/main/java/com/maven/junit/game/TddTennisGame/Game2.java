package com.maven.junit.game.TddTennisGame;

import java.util.ArrayList;

import iInterface.IGame;

public class Game2 implements IGame{
	
	// VARIABLES
	private Player2 player1;
	private Player2 player2;
	private boolean isOver;
	private State winner;
	private ArrayList<String> historicScorePlayer1;
	private ArrayList<String> historicScorePlayer2;
	
	private boolean deuce;
	private State hasAdvance;

	// CONTRUCTORS	
	public Game2(Player2 _player1, Player2 _player2) {
		super();
		this.player1 = _player1;
		this.player2 = _player2;
		this.isOver = false;
		this.winner = State.NONE;
		historicScorePlayer1 = new ArrayList<String>();
		historicScorePlayer2 = new ArrayList<String>();
		this.initScore();
		this.deuce = false;
		this.hasAdvance = State.NONE;
	}
	public Game2() {
		super();
	};
	
	// GETTERS & SETTERS
	@Override
	public Player2 getPlayer1() {
		return this.player1;
	}

	@Override
	public Player2 getPlayer2() {
		return this.player2;
	}

	@Override
	public int getScorePlayer1() {
		return this.player1.getScore();
	}

	@Override
	public int getScorePlayer2() {
		return this.player2.getScore();
	}
	
	@Override
	public ArrayList<String> getHistoricScorePlayer1() {
		return this.historicScorePlayer1;
	}

	@Override
	public ArrayList<String> getHistoricScorePlayer2() {
		return this.historicScorePlayer2;
	}

	@Override
	public boolean gameOver() {
		return this.isOver;
	}
	
	@Override
	public boolean getDeuce() {
		return this.deuce;
	}
	
	@Override
	public State getAdvance() {
		return this.hasAdvance;
	}
	
	 /**
     * @return the name of the winner or none if no winner;
     */
	@Override
	public Player2 getWinner() {
		if(this.winner.equals(State.P1)) 
			return this.getPlayer1();
		else if(this.winner.equals(State.P2)) 
			return this.getPlayer2();
		else 
			return null;			
	}
	
	 /**
	 * update the score of the player who win a point
	 * @param Player player
	 * @pre !gameOver
     * @return set the score of the player
     */
	@Override
	public void winPoint(Player2 player) {
		if(!this.gameOver())
			this.setScore(player);
		//this.printResult();
	}	
	
	/**
	 * increment the score of the player who win a point
	 * @param Player player
	 * @pre (!deuce || (deuce && (hasAdvance.equals(State.P1 || State.P2))))
     * @return set the score of the player or return the winner
     * @return gameOver or not
     */
	@Override
	public void setScore(Player2 player) {
		if(!this.getDeuce()) {
			if(player.getScore() == 0) player.setScore(15);
			else if(player.getScore() == 15) player.setScore(30);
			else if(player.getScore() == 30) player.setScore(40);
			else {
				player.setScore(50);
				this.isOver = true;
				if(this.player1.equals(player))
					this.winner = State.P1;
				else if(this.player2.equals(player))
					this.winner = State.P2;
			}
			
			if((this.getScorePlayer1() == 40 && this.getScorePlayer2() == 40) && !this.getDeuce())
				this.deuce = true;
			
			this.historicScorePlayer1.add(this.getScorePlayer1()+"");	
			this.historicScorePlayer2.add(this.getScorePlayer2()+"");
		}
		
		else if(this.getDeuce() && this.hasAdvance.equals(State.NONE)) {
			if(this.getPlayer1().equals(player)) {
				this.hasAdvance = State.P1;
				historicScorePlayer1.add("40(*)");
				historicScorePlayer2.add("40");
			}else if(this.getPlayer2().equals(player)) {
				this.hasAdvance = State.P2;
				historicScorePlayer1.add("40");
				historicScorePlayer2.add("40(*)");
			}
		}
		
		else if(this.hasAdvance.equals(State.P1) && this.getPlayer1().equals(player)) {
			player.setScore(50);
			this.isOver = true;
			this.winner = State.P1;
			this.historicScorePlayer1.add("Win_Game");	
			this.historicScorePlayer2.add("40");
		}
		else if(this.hasAdvance.equals(State.P2) && this.getPlayer2().equals(player)) {
			player.setScore(50);
			this.isOver = true;
			this.winner = State.P2;
			this.historicScorePlayer1.add("40");	
			this.historicScorePlayer2.add("Win_Game");
		}
		else {
			this.hasAdvance = State.NONE;
			historicScorePlayer1.add("40");
			historicScorePlayer2.add("40");
		}
			
	}
	
	/**
	 * initialize players score
     * @return player1.score = 0; player2.score = 0;
     * @return historicScorePlayer1 = 0; historicScorePlayer2 = 0;
     */
	@Override
	public void initScore() {
		this.player1.setScore(0);
		this.player2.setScore(0);
		historicScorePlayer1.add("0");
		historicScorePlayer2.add("0");
	}
	
	@Override
	public void printResult() {
		System.out.println("GAME INFO");
		System.out.print(this.getPlayer1().getName()+"\t| ");
		for(int i=0; i<historicScorePlayer1.size(); i++) {
			if(historicScorePlayer1.get(i).equals("50"))
				System.out.print("Win game|");
			else
				System.out.print(historicScorePlayer1.get(i)+"\t|");
		}
		System.out.println("");
		System.out.print(this.getPlayer2().getName()+"\t| ");
		for(int i=0; i<historicScorePlayer2.size(); i++) {
			if(historicScorePlayer2.get(i).equals("50"))
				System.out.print("Win game|");
			else
				System.out.print(historicScorePlayer2.get(i)+"\t|");
		}
		System.out.println("");
		System.out.println("THE WINNER IS : "+ (this.getWinner()!= null?this.getWinner().getName():"NONE"));
		System.out.println("");
	}
	
}
