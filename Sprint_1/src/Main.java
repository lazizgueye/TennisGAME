package javaTennisGAME;

import java.util.Random;

public class Main {
	public static void main (String arg[])
	{
		Player p1= new Player("P1");
		Player p2= new Player("P2");
		
		/*Game g = new Game(p1, p2);
		Random rand = new Random();
		int n;
		
		while(!g.gameOver()) {
			n = rand.nextInt(50) + 1;				
			if(n<20) 
				g.winPoint(g.getIndexPlayers("P1"));				
			else if(n>=30 && n<=50) 	
				g.winPoint(g.getIndexPlayers("P2"));				
		}
		
		g.printHistoric();
		System.out.println(g.getScore_playe1() +"###########"+ g.getScore_playe2());
		System.out.println(g.getWinner());*/
		
		
		Set s = new Set(p1, p2);
		s.playSet();
		s.printHistoric();
		s.printScore();
		System.out.println(s.getWinner());
		//s.Games.get(0).printHistoric();
		//System.out.println(s.Games.get(0).getWinner());
	}
}
