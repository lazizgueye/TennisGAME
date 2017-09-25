package javaTennisGAME;

public class Main {
	public static void main (String arg[])
	{
		Player p1= new Player("P1");
		Player p2= new Player("P2");
		
		Game g = new Game();
		g.addPlayers(p1);
		g.addPlayers(p2);
		//g.getWinner();		
		g.gameStart();
		
	}
}
