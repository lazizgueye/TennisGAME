package iInterface;

import java.util.ArrayList;

import com.maven.junit.game.TddTennisGame.Game2;
import com.maven.junit.game.TddTennisGame.Player2;

public interface ISet {
	
	enum State {P1, P2, NONE};
	
	public Player2 getPlayer1();
	public Player2 getPlayer2();
	public int getScorePlayer1();
	public int getScorePlayer2();
	public ArrayList<Game2> getHistoricScoreGame();
	public ArrayList<Integer> getHistoricScoreSetP1();
	public ArrayList<Integer> getHistoricScoreSetP2();
	
	public int getGameNumber();
	
	public Player2 getWinner();		
	public boolean getGameOver();
	public boolean getTieBreak();
		
	public void initScore();
	public void runGame(Game2 game);
	public void setScore(Game2 game);
	
	public void printResult();
	
	
}
