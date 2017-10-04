package com.maven.junit.game.TddTennisGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import iInterface.IGame.State;

public class SetTest {
	
		
	@Test
	public void setStartScoreOfPlayerEquals0() {
		System.out.println("setStartScoreOfPlayerEquals0");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Set s = new Set(p1, p2);
		assertEquals(0, s.getScorePlayer1());
		assertEquals(0, s.getScorePlayer2());
		assertFalse(s.getGameOver());
		assertEquals(0, s.getGameNumber());
	}
	
	@Test
	public void scoreChangeWhenPlayerWinAGame() {
		System.out.println("scoreChangeWhenPlayerWinAGame");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		s.runGame(g2);
		s.runGame(g1);
		
		assertEquals(1, s.getScorePlayer1());
		assertEquals(1, s.getScorePlayer2());
		
		s.runGame(g2);
		
		assertEquals(1, s.getScorePlayer1());
		assertEquals(2, s.getScorePlayer2());
	}
	
	@Test
	public void noWinnerWhenScoreEquals6And5() {
		System.out.println("noWinnerWhenScoreEquals6And5");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		
		assertEquals(5, s.getScorePlayer1());
		assertEquals(5, s.getScorePlayer2());
		
		s.runGame(g1);
		
		assertEquals(6, s.getScorePlayer1());
		assertEquals(5, s.getScorePlayer2());
		assertFalse(s.getGameOver());
		assertEquals(null, s.getWinner());
	}
	
	@Test
	public void winningSetWith6Points() {
		System.out.println("winningSetWithoutTieBreak");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		s.runGame(g2);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		
		assertEquals(6, s.getScorePlayer1());
		assertEquals(2, s.getScorePlayer2());
		assertFalse(s.getTieBreak());
		assertEquals(p1, s.getWinner());
		assertTrue((s.getScorePlayer1()-s.getScorePlayer2()) >= 2);
	}
	
	@Test
	public void tieBreakActivated() {
		System.out.println("tieBreakActivated");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g1);
		
		assertEquals(6, s.getScorePlayer1());
		assertEquals(6, s.getScorePlayer2());
		assertTrue(s.getTieBreak());
		assertEquals(12, s.getGameNumber());
		assertFalse(s.getGameOver());
		assertEquals(null, s.getWinner());
	}
	
	@Test
	public void noWinnerAndbeSureTieBreakWasActivatedWhenScoreIs8And7() {
		System.out.println("tieBreakActivated");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);		
		s.runGame(g1);	// 6 - 6
		
		s.runGame(g1);
		s.runGame(g2);	
		s.runGame(g2);	// 7 - 8
		
		assertEquals(7, s.getScorePlayer1());
		assertEquals(8, s.getScorePlayer2());
		assertTrue(s.getTieBreak());
//		/assertEquals(12, s.getGameNumber());
		assertFalse(s.getGameOver());
		assertEquals(null, s.getWinner());
	}
	
	@Test
	public void winnerAndbeSureTieBreakWasActivatedWhenScoreIs9And7() {
		System.out.println("tieBreakActivated");
		Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)	
		
		Game2 g2 = new Game2(p1, p2);
		g2.winPoint(p1);	//	15 - 0
		g2.winPoint(p2);	//	15 - 15
		g2.winPoint(p1);	//	30 - 15
		g2.winPoint(p2);	//	30 - 30
		g2.winPoint(p2);	//	30 - 40
		g2.winPoint(p2);	//	30 - 50		(p2 win)
		
		Game2 g3 = new Game2(p1, p2);
		g3.winPoint(p1);	//	15 - 0
		g3.winPoint(p1);	//	30 - 0
		g3.winPoint(p1);	//	40 - 0
		g3.winPoint(p2);	//	 0 - 15
		g3.winPoint(p2);	//	 0 - 30
		g3.winPoint(p2);	//	 0 - 40			deuce activate
		g3.winPoint(p1);	//	40* - 40	(*) p1 has advance
		g3.winPoint(p2);	//	40 - 40			p1 loose advance
		g3.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g3.winPoint(p1);	//	40 - 40			p2 loose advance
		g3.winPoint(p1);	//	40* - 40	(*) advance
		g3.winPoint(p2);	//	40 - 40			p1 loose advance
		g3.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g3.winPoint(p2);	//	40 - 50		(*) p2 win game
		//g3.printResult();	
		
		Game2 g4 = new Game2(p1, p2);
		g4.winPoint(p1);	//	15 - 0
		g4.winPoint(p1);	//	30 - 0
		g4.winPoint(p1);	//	40 - 0
		g4.winPoint(p2);	//	 0 - 15
		g4.winPoint(p2);	//	 0 - 30
		g4.winPoint(p2);	//	 0 - 40			deuce activate
		g4.winPoint(p1);	//	40* - 40	(*) p1 has advance
		g4.winPoint(p2);	//	40 - 40			p1 loose advance
		g4.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g4.winPoint(p1);	//	40 - 40			p2 loose advance
		g4.winPoint(p1);	//	40* - 40	(*) advance
		g4.winPoint(p1);	//	40 - 40			p1 win
		//g4.printResult();	
		
		s.runGame(g1);	// 1 - 0
		s.runGame(g4);	// 2 - 0
		s.runGame(g1);	// 3 - 0
		s.runGame(g1);	// 4 - 0
		s.runGame(g4);	// 5 - 0
		s.runGame(g3);	// 5 - 1
		s.runGame(g2);	// 5 - 2
		s.runGame(g2);	// 5 - 3
		s.runGame(g3);	// 5 - 4
		s.runGame(g2);	// 5 - 5
		s.runGame(g2);	// 5 - 6
		s.runGame(g1);	// 6 - 6
		
		s.runGame(g4);	// 7 - 6
		s.runGame(g3);	// 7 - 7
		s.runGame(g2);	// 7 - 8
		s.runGame(g2);	// 7 - 9
		
		s.printResult();
		
		assertTrue(s.getScorePlayer1() >= 6);
		assertTrue(s.getScorePlayer2() >= 6);
		assertTrue(Math.abs(s.getScorePlayer2()-s.getScorePlayer1()) >=2 );
		assertTrue(s.getTieBreak());
		assertTrue(s.getGameOver());
		assertEquals(p2, s.getWinner());
	}
	
}
