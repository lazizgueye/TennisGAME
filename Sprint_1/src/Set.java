package javaTennisGAME;

import java.util.ArrayList;
import java.util.Random;

public class Set{
	
	// Variables
	public static int INIT_NUMBER = 99;
	
	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Game> Games = new ArrayList<Game>();
	ArrayList<String> historic_set_scoreP1 = new ArrayList<String>();
	ArrayList<String> historic_set_scoreP2 = new ArrayList<String>();
	
	private Boolean over = false;
	private int winner = INIT_NUMBER;
	private int game_number = 0;
	private Boolean tie_break = false;
	
	private int score_set_player1 = 0;
	private int score_set_player2 = 0;
	
	// Constructors
	public Set(Player player1, Player player2) {
		Game game1 = new Game(player1, player2);
		this.Games.add(game1);
		this.Players.add(player1);
		this.Players.add(player2);
		addHistorical(score_set_player1+"", score_set_player2+"");
	}
	
	// Getters && Setters
	public int getScore_set_playe1() {
		return this.score_set_player1;
	}
	public int getScore_set_playe2() {
		return this.score_set_player2;
	}
	public Boolean matchOver() {
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
		if(this.getScore_set_playe1()<this.getScore_set_playe2())
			return 1;
		return 0;
	}
	
	// Methods
	public void playSet() {
		while(!matchOver()) {			
			this.Games.get(game_number).playGame();
			
			/* increment players score */
			if(this.Games.get(game_number).getWinner()==0)
				this.score_set_player1++;
			else
				this.score_set_player2++;
			
			addHistorical(score_set_player1+"", score_set_player2+"");
			
			/* game winner, tie_break rule */
			if((this.score_set_player1 == 6 && this.score_set_player2 <=4)|| (this.score_set_player2 == 6 && this.score_set_player1 <= 4)) {
				this.over = true;
			}
			if((this.score_set_player1 == 7 || this.score_set_player2 == 7) && !this.tie_break) {
				this.over = true;
			}
			else if(this.score_set_player1 == 6 && this.score_set_player2 == 6) {
				this.tie_break = true;
			}
			else if((((this.score_set_player1 - this.score_set_player2)>=2 && this.score_set_player1>=6)|| 
					((this.score_set_player2 - this.score_set_player1)>=2 && this.score_set_player2>=6)) && this.tie_break) {
				this.over = true;
			}			
			else {
				game_number++;
				Game newGame = new Game(this.Players.get(0), this.Players.get(1));
				this.Games.add(newGame);
			}
		}		
	}
	
	public void addHistorical(String score1, String score2) {
		this.historic_set_scoreP1.add(score1);
		this.historic_set_scoreP2.add(score2);
	}
	
	// Print
	public void printScore() {
		System.out.println("P1 : " + this.getScore_set_playe1());
		System.out.println("P2 : " + this.getScore_set_playe2());
	}
	
	public void printHistoric() {
		System.out.print("P1 | ");
		for(int i=0; i<this.historic_set_scoreP1.size(); i++) {
			System.out.print(this.historic_set_scoreP1.get(i)+"\t|");
		}
		System.out.println("");
		System.out.print("P2 | ");
		for(int i=0; i<this.historic_set_scoreP2.size(); i++) {
			System.out.print(this.historic_set_scoreP2.get(i)+"\t|");
		}
		System.out.println("");
		System.out.println("");
	}
}
