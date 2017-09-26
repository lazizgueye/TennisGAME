package javaTennisGAME;

import java.util.ArrayList;
import java.util.Random;

public class Game{
	
	// Variables
	public static int INIT_NUMBER = 99;
	
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList historic_scoreP1 = new ArrayList();
	ArrayList historic_scoreP2 = new ArrayList();
	
	private Boolean over = false;
	private int winner = INIT_NUMBER;
	private Boolean deuce = false;
	private int advantage;
	
	private int score_player1 = 0;
	private int score_player2 = 0;
	
	// Constructors
	public Game(Player player1, Player player2) {
		player1.setScore(0);
		player2.setScore(0);
		this.Players.add(player1);
		this.Players.add(player2);
		addHistorical(player1.getScore()+"", player2.getScore()+"");
	}
	
	// Getters && Setters
	public int getScore_playe1() {
		return this.score_player1;
	}
	public int getScore_playe2() {
		return this.score_player2;
	}
	public Boolean gameOver() {
		return this.over;
	}	
	public int getIndexPlayers(String _name) {
		for(int i=0; i<Players.size(); i++) {
			if(this.Players.get(i).getName().equals(_name))
				return i;
		}
		return 0;
	}	
	public int getWinner() {
		return this.winner;			
	}	
	
	// Methods
	public void playGame() {
		Random rand = new Random();
		int n;
		
		while(!gameOver()) {
			n = rand.nextInt(50) + 1;				
			if(n<20) 
				winPoint(0);				
			else if(n>=30 && n<=50) 	
				winPoint(1);				
		}		
	}
	
	public void winPoint(int idPlayer) {
		if(this.deuce.equals(true)) {
			if(this.advantage == idPlayer) {
				this.Players.get(idPlayer).setScore();
				this.winner = idPlayer;
				this.over = true;				
				this.score_player1 = this.Players.get(0).getScore();
				this.score_player2 = this.Players.get(1).getScore();
				addHistorical(this.Players.get(0).getScore()+"", this.Players.get(1).getScore()+"");
			}
			else if(this.advantage == INIT_NUMBER) {
				this.advantage = idPlayer;
			}
			else {
				this.advantage = INIT_NUMBER;
			}
			if(!over)
				this.printAdvantage(this.advantage);
			
		}
		else if(this.Players.get(0).getScore() == 40 && this.Players.get(1).getScore() == 40) {
			this.deuce = true;
			this.advantage = idPlayer;
			this.printAdvantage(this.advantage);
		}
		else {
			this.Players.get(idPlayer).setScore();
			if(this.Players.get(idPlayer).getScore()>40) {
				this.winner = idPlayer;
				this.over = true;
			}
			this.score_player1 = this.Players.get(0).getScore();
			this.score_player2 = this.Players.get(1).getScore();
			addHistorical(this.Players.get(0).getScore()+"", this.Players.get(1).getScore()+"");
		}
	}
	
	public void addHistorical(String score1, String score2) {
		this.historic_scoreP1.add(score1);
		this.historic_scoreP2.add(score2);
	}
	
	// Prints
	public void print() {
		System.out.println("----------");
		this.Players.get(0).print();
		this.Players.get(1).print();
	}
	public void printAdvantage(int number) {
		if(number == 0) 
			addHistorical(this.Players.get(0).getScore()+" (*)", this.Players.get(1).getScore()+"");
		else if(number == 1) 
			addHistorical(this.Players.get(0).getScore()+"", this.Players.get(1).getScore()+ " (*)");
		else 
			addHistorical(this.Players.get(0).getScore()+"", this.Players.get(1).getScore()+ "");
	}
	
	public void printHistoric() {
		System.out.print("P1 | ");
		for(int i=0; i<historic_scoreP1.size(); i++) {
			System.out.print(historic_scoreP1.get(i)+"\t|");
		}
		System.out.println("");
		System.out.print("P2 | ");
		for(int i=0; i<historic_scoreP2.size(); i++) {
			System.out.print(historic_scoreP2.get(i)+"\t|");
		}
		System.out.println("");
		System.out.println("");
	}
}
