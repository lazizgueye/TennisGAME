package com.maven.junit.game.TddTennisGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import iInterface.IGame.State;

public class GameTest {
	
		
	@Test
	public void initScore() {
		System.out.println("test the init score of players");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.printResult();
		assertEquals(0, g.getScorePlayer1(), g.getScorePlayer2());
	}
	
	@Test
	public void scoreAtferWinningPoint() {
		System.out.println("scoreAtferWinningPoint");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);
		g.printResult();
		assertEquals(15, g.getScorePlayer1());
		assertEquals(0, g.getScorePlayer2());
	}
	
	@Test
	public void winnigGameWithoutDeuceAndAdvance() {
		System.out.println("winnigGameWithoutDeuceAndAdvance");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p1);	//	30 - 0
		g.winPoint(p1);	//	40 - 0
		g.winPoint(p2);	//	 0 - 15
		g.winPoint(p2);	//	 0 - 30
		g.winPoint(p1);	//	50 - 30		(p1 win)
		g.printResult();
		assertTrue(g.getScorePlayer1()>40);
		assertTrue(g.getScorePlayer2()<40);
		assertFalse(g.getDeuce());
		assertEquals(State.NONE, g.getAdvance());
		assertEquals("Aziz", g.getPlayer1().getName());
		assertEquals("Aziz", g.getWinner().getName());
	}
	
	@Test
	public void winnigGameWithoutDeuceAndAdvance2() {
		System.out.println("winnigGameWithoutDeuceAndAdvance2");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p2);	//	15 - 15
		g.winPoint(p1);	//	30 - 15
		g.winPoint(p2);	//	30 - 30
		g.winPoint(p2);	//	30 - 40
		g.winPoint(p2);	//	30 - 50		(p2 win)
		g.printResult();
		assertTrue(g.getScorePlayer1()<40);
		assertTrue(g.getScorePlayer2()>40);
		assertFalse(g.getDeuce());
		assertEquals(State.NONE, g.getAdvance());
		assertEquals("Leo", g.getWinner().getName());
	}
	
	@Test
	public void deuceActivateAndHasAdvance() {
		System.out.println("deuceActivateAndHasAdvance");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p1);	//	30 - 0
		g.winPoint(p1);	//	40 - 0
		g.winPoint(p2);	//	 0 - 15
		g.winPoint(p2);	//	 0 - 30
		g.winPoint(p2);	//	 0 - 40
		g.winPoint(p1);	//	40* - 40	(*) advance
		g.printResult();
		assertEquals(40, g.getScorePlayer1());
		assertEquals(g.getScorePlayer1(),g.getScorePlayer2());
		assertTrue(g.getDeuce());
		assertEquals(State.P1, g.getAdvance());
		assertEquals(null, g.getWinner());
	}
	
	@Test
	public void deuceActivateAndLooseAdvance() {
		System.out.println("deuceActivateAndLooseAdvance");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p1);	//	30 - 0
		g.winPoint(p1);	//	40 - 0
		g.winPoint(p2);	//	 0 - 15
		g.winPoint(p2);	//	 0 - 30
		g.winPoint(p2);	//	 0 - 40
		g.winPoint(p1);	//	40* - 40	(*) advance
		g.winPoint(p2);	//	40 - 40
		g.printResult();	
		assertEquals(40, g.getScorePlayer1());
		assertEquals(g.getScorePlayer1(),g.getScorePlayer2());
		assertTrue(g.getDeuce());
		assertEquals(State.NONE, g.getAdvance());
		assertEquals(null, g.getWinner());
	}
	
	@Test
	public void winningGameDeuceActivateAndHasAdvance() {
		System.out.println("winningGameDeuceActivateAndHasAdvance");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p1);	//	30 - 0
		g.winPoint(p1);	//	40 - 0
		g.winPoint(p2);	//	 0 - 15
		g.winPoint(p2);	//	 0 - 30
		g.winPoint(p2);	//	 0 - 40			deuce activate
		g.winPoint(p1);	//	40* - 40	(*) p1 has advance
		g.winPoint(p2);	//	40 - 40			p1 loose advance
		g.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g.winPoint(p1);	//	40 - 40			p2 loose advance
		g.winPoint(p1);	//	40* - 40	(*) advance
		g.winPoint(p2);	//	40 - 40			p1 loose advance
		g.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g.winPoint(p2);	//	40 - 50		(*) p2 win game
		g.printResult();	
		assertTrue(g.getScorePlayer2()>40);
		assertEquals(40, g.getScorePlayer1());
		assertTrue(g.getDeuce());
		assertEquals(State.P2, g.getAdvance());
		assertEquals("Leo", g.getWinner().getName());
	}
	
	@Test
	public void winningGameDeuceActivateAndHasAdvance2() {
		System.out.println("winningGameDeuceActivateAndHasAdvance2");
		Player2 p1 = new Player2("Aziz");
		Player2 p2 = new Player2("Leo");
		Game2 g = new Game2(p1, p2);
		g.winPoint(p1);	//	15 - 0
		g.winPoint(p1);	//	30 - 0
		g.winPoint(p1);	//	40 - 0
		g.winPoint(p2);	//	 0 - 15
		g.winPoint(p2);	//	 0 - 30
		g.winPoint(p2);	//	 0 - 40			deuce activate
		g.winPoint(p1);	//	40* - 40	(*) p1 has advance
		g.winPoint(p2);	//	40 - 40			p1 loose advance
		g.winPoint(p2);	//	40 - 40*	(*) p2 has advance
		g.winPoint(p1);	//	40 - 40			p2 loose advance
		g.winPoint(p1);	//	40* - 40	(*) advance
		g.winPoint(p1);	//	40 - 40			p1 win
		g.printResult();	
		assertTrue(g.getScorePlayer1()>40);
		assertEquals(40, g.getScorePlayer2());
		assertTrue(g.getDeuce());
		assertEquals(State.P1, g.getAdvance());
		assertEquals("Aziz", g.getWinner().getName());
	}
	
}
