package com.maven.junit.game.TddTennisGame;

import java.util.ArrayList;

import iInterface.ISet;

public class Set implements ISet{
	
	// VARIABLES
	private Player2 player1;
	private Player2 player2;
	private int scorePlayer1;
	private int scorePlayer2;
	private ArrayList<Game2> historicScoreGame;
	private ArrayList<Integer> historicScoreSetP1;
	private ArrayList<Integer> historicScoreSetP2;
	private int gameNumber;
	private boolean gameOver;
	private State winner;
	private Boolean tieBreak;

	// CONSTRUCTORS
	public Set(Player2 player1, Player2 player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.historicScoreGame = new ArrayList<Game2>();
		this.historicScoreSetP1 = new ArrayList<Integer>();
		this.historicScoreSetP2 = new ArrayList<Integer>();
		this.gameNumber = 0;
		this.gameOver = false;
		this.tieBreak = false;
		this.scorePlayer1 = 0;
		this.scorePlayer2 = 0;
		this.winner = State.NONE;
		initScore();
	}
	
	public Set() {
		super();
	}
	
	// GETTERS
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
		return this.scorePlayer1;
	}

	@Override
	public int getScorePlayer2() {
		return this.scorePlayer2;
	}

	@Override
	public ArrayList<Game2> getHistoricScoreGame() {
		return this.historicScoreGame;
	}
	
	@Override
	public ArrayList<Integer> getHistoricScoreSetP1() {
		return this.historicScoreSetP1;
	}
	
	@Override
	public ArrayList<Integer> getHistoricScoreSetP2() {
		return this.historicScoreSetP2;
	}

	@Override
	public int getGameNumber() {
		return this.gameNumber;		
	}

	@Override
	public Player2 getWinner() {
		if(this.winner.equals(State.P1))
			return this.getPlayer1();
		else if(this.winner.equals(State.P2))
			return this.getPlayer2();
		else
			return null;
	}

	@Override
	public boolean getGameOver() {
		return this.gameOver;
	}
	
	@Override
	public boolean getTieBreak() {
		return this.tieBreak;
	}

	// METHODS
	@Override
	public void initScore() {
		this.player1.setScore(0);
		this.player2.setScore(0);
	}

	@Override
	public void runGame(Game2 game) {
		if(!this.gameOver) {
			this.setScore(game);	
			this.gameNumber++;
			this.historicScoreGame.add(game);
			this.historicScoreSetP1.add(this.getScorePlayer1());
			this.historicScoreSetP2.add(this.getScorePlayer2());
		}
	}

	@Override
	public void setScore(Game2 game) {	
		if(game.getWinner().equals(this.player1))
			this.scorePlayer1 += 1;
		else if(game.getWinner().equals(this.player2)) 
			this.scorePlayer2 += 1;
	
		/* game winner, tie_break rule */
		if((this.getScorePlayer1() == 6 && this.getScorePlayer2() <=4)|| 
				(this.getScorePlayer2() == 6 && this.getScorePlayer1() <= 4)) {
			this.gameOver = true;
		}
		else if((this.getScorePlayer1() == 7 || this.getScorePlayer2() == 7) && !this.getTieBreak()) {
			this.gameOver = true;
		}
		else if(this.getScorePlayer1() == 6 && this.getScorePlayer2() == 6) {
			this.tieBreak = true;
			this.winner = State.NONE;
		}
		else if((((this.getScorePlayer1() - this.getScorePlayer2())>=2 && this.getScorePlayer1()>=6)|| 
				((this.getScorePlayer2() - this.getScorePlayer1())>=2 && this.getScorePlayer2()>=6)) &&
				this.getTieBreak()) {
			this.gameOver = true;
		}
		
		if((this.getScorePlayer1()>this.getScorePlayer2()) && this.getGameOver())
			this.winner = State.P1;
		else if((this.getScorePlayer1()<this.getScorePlayer2()) && this.getGameOver())
			this.winner = State.P2;
	}

	@Override
	public void printResult() {		
		System.out.println("SET INFO");
		System.out.println("");
		System.out.println("Players\t\t|Set Win\t|Game Win");
		System.out.println("");
		for(int i=0; i<this.historicScoreGame.size(); i++) {
			String printP1 = "";
			String printP2 = "";
			for(int j=0; j<this.getHistoricScoreGame().get(i).getHistoricScorePlayer1().size(); j++) {
				if(this.getHistoricScoreGame().get(i).getHistoricScorePlayer1().get(j).equals("50"))
					printP1 += "Win Game|";
				else
					printP1 += this.getHistoricScoreGame().get(i).getHistoricScorePlayer1().get(j)+"\t|";
				
				if(this.getHistoricScoreGame().get(i).getHistoricScorePlayer2().get(j).equals("50"))
					printP2 += "Win Game|";
				else
					printP2 += this.getHistoricScoreGame().get(i).getHistoricScorePlayer2().get(j)+"\t|";
			}
			
			System.out.println("_________ GAME "+(i+1)+" _____________________\n");	
			System.out.println(this.player1.getName()+"\t\t|"+ this.getHistoricScoreSetP1().get(i)+"\t\t|"+printP1);			
			System.out.println("----------------|--------------\t\t|");	
			System.out.println(this.player2.getName()+"\t\t|"+ this.getHistoricScoreSetP2().get(i)+"\t\t|"+printP2);
			System.out.println("");	
		}
		
		System.out.println("");	
		System.out.println("THE WINNER IS : "+ (this.getWinner()!= null?this.getWinner().getName():"NONE"));
	}

}
