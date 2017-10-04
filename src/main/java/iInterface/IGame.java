package iInterface;

import java.util.ArrayList;

import com.maven.junit.game.TddTennisGame.Player2;

public interface IGame {
	
	enum State {P1, P2, NONE};
	
	public Player2 getPlayer1();
	public Player2 getPlayer2();
	public int getScorePlayer1();
	public int getScorePlayer2();
	public ArrayList<String> getHistoricScorePlayer1();
	public ArrayList<String> getHistoricScorePlayer2();
	
	public Player2 getWinner();
	public boolean getDeuce();
	public State getAdvance();	
	public boolean gameOver();
		
	public void initScore();
	public void winPoint(Player2 player);
	public void setScore(Player2 player);
	
	public void printResult();
	
	
}
