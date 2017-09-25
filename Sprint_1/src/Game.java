package javaTennisGAME;

import java.util.ArrayList;
import java.util.Random;

public class Game{
	
	public static int INIT_NUMBER = 99;
	ArrayList<Player> Players = new ArrayList<Player>();
	private Boolean gameOver = false;
	private int winner = INIT_NUMBER;
	private Boolean deuce = false;
	private int advantage;
	
	
	public Game() {}
	
	public Boolean getGameOver() {return this.gameOver;}
	/*public void getWinner() {
		if(this.winner == INIT_NUMBER)
			System.out.println("No Winner");
		else
			this.Players.get(this.winner).print();			
	}*/
	
	public void addPlayers(Player p) {
		this.Players.add(p); 
		p.print();
	}
	
	public void gameStart() {
		Random rand = new Random();
		int n;
		
		while(this.gameOver.equals(false)) {
			n = rand.nextInt(50) + 1;				
			if(n<20) 
				this.gameScore(0);				
			else if(n>=30 && n<=50) 	
				this.gameScore(1);				
		}
	}
	
	public void gameScore(int idPlayer) {
		if(this.deuce.equals(true)) {
			if(this.advantage == idPlayer) {
				this.Players.get(idPlayer).setScore();
				this.winner = idPlayer;
				this.gameOver = true;
				this.print();
				System.exit(0);
			}
			else if(this.advantage == INIT_NUMBER) {
				this.advantage = idPlayer;
			}
			else {
				this.advantage = INIT_NUMBER;
			}
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
				this.gameOver = true;
			}
			this.print();
		}
	}
	
	public void print() {
		System.out.println("----------");
		this.Players.get(0).print();
		this.Players.get(1).print();
	}
	public void printAdvantage(int number) {
		if(number == 0) {
			System.out.println("**********");
			System.out.println(this.Players.get(0).getName() + " : " + this.Players.get(0).getScore() + " (*)");
			System.out.println(this.Players.get(1).getName() + " : " + this.Players.get(1).getScore());
		}
		else if(number == 1) {
			System.out.println("**********");
			System.out.println(this.Players.get(0).getName() + " : " + this.Players.get(0).getScore());
			System.out.println(this.Players.get(1).getName() + " : " + this.Players.get(1).getScore() + " (*)");
		}
		else {
			System.out.println("**********");
			System.out.println(this.Players.get(0).getName() + " : " + this.Players.get(0).getScore());
			System.out.println(this.Players.get(1).getName() + " : " + this.Players.get(1).getScore());
		}
	}
}
