package com.maven.junit.game.TddTennisGame;


public class Main2 {
	public static void main (String arg[])
	{
		/*Player2 p1= new Player2("P1");
		Player2 p2= new Player2("P2");	
		Set s = new Set(p1, p2);
		
		Game2 g1 = new Game2(p1, p2);
		g1.winPoint(p1);	//	15 - 0
		g1.winPoint(p1);	//	30 - 0
		g1.winPoint(p1);	//	40 - 0
		g1.winPoint(p2);	//	 0 - 15
		g1.winPoint(p2);	//	 0 - 30
		g1.winPoint(p1);	//	50 - 30		(p1 win)		
		//g1.printResult();
		
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

		
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g2);
		s.runGame(g1);
		s.runGame(g2);
		s.runGame(g1);
		s.runGame(g1);
		s.runGame(g1);
		s.printResult();
		/*System.out.println(s.getScorePlayer1());
		System.out.println(s.getScorePlayer2());
		System.out.println(s.getWinner().getName());*/
		
	}
}
